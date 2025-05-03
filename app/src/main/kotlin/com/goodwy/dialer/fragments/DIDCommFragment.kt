package com.goodwy.dialer.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goodwy.commons.extensions.*
import com.goodwy.commons.helpers.*
import com.goodwy.commons.views.MyGridLayoutManager
import com.goodwy.commons.views.MyLinearLayoutManager
import com.goodwy.dialer.R
import com.goodwy.dialer.activities.SimpleActivity
import com.goodwy.dialer.adapters.CredentialsAdapter
import com.goodwy.dialer.databinding.FragmentDidcommBinding
import com.goodwy.dialer.extensions.config
import com.goodwy.dialer.extensions.showInfoDialog
import com.goodwy.dialer.interfaces.RefreshItemsListener

class DIDCommFragment : Fragment(), RefreshItemsListener {
    private lateinit var didCommBinding: FragmentDidcommBinding
    private var connections = ArrayList<DIDConnection>()
    private var credentials = ArrayList<Credential>()

    // DIDComm service interface - to be implemented by the hosting activity
    interface DIDCommService {
        fun scanQRCodeForConnection(callback: (String) -> Unit)
        fun establishConnection(qrContent: String, callback: (DIDConnection) -> Unit)
        fun fetchCredentials(connectionId: String, callback: (List<Credential>) -> Unit)
    }

    data class DIDConnection(
        val id: String,
        val name: String,
        val did: String,
        val dateConnected: Long,
        val iconRes: Int = R.drawable.ic_contacts
    )

    data class Credential(
        val id: String,
        val issuer: String,
        val type: String,
        val claims: Map<String, String>,
        val dateIssued: Long,
        val connectionId: String
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_didcomm, container, false)
        didCommBinding = FragmentDidcommBinding.bind(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        didCommBinding.root.setBackgroundColor(requireContext().getProperBackgroundColor())
        setupViews()
        refreshItems()
    }

    private fun setupViews() {
        didCommBinding.apply {
            scanQrButton.setOnClickListener {
                scanQRAndEstablishConnection()
            }

            switchViewButton.setOnClickListener {
                toggleViewType()
            }
        }

        didCommBinding.switchViewButton.setOnClickListener {
            toggleViewType()

            // Update button icon to reflect current view type
            val iconRes = when (requireContext().config.didCommViewType) {
                VIEW_TYPE_GRID -> R.drawable.ic_article_vector
                else -> R.drawable.ic_grid
            }
            didCommBinding.switchViewButton.setIconResource(iconRes)
        }

        val initialIconRes = when (requireContext().config.didCommViewType) {
            VIEW_TYPE_GRID -> R.drawable.ic_article_vector
            else -> R.drawable.ic_grid
        }
        didCommBinding.switchViewButton.setIconResource(initialIconRes)


        didCommBinding.didcommFragment.fragmentPlaceholder.text = requireContext().getString(R.string.no_connections_found)
        didCommBinding.didcommFragment.fragmentPlaceholder2.beGone()
        didCommBinding.didcommFragment.letterFastscrollerThumb.beGone()
        didCommBinding.didcommFragment.letterFastscroller.beGone()
    }

    private fun scanQRAndEstablishConnection() {
        val activity = requireActivity() as? DIDCommService ?: return
        val context = requireContext()

        activity.scanQRCodeForConnection { qrContent ->
            activity.establishConnection(qrContent) { connection ->
                (context as? Activity)?.runOnUiThread {
                    connections.add(connection)
                    updateConnectionsList()
                    fetchCredentialsForConnection(connection.id)
                }
            }
        }
    }

    private fun fetchCredentialsForConnection(connectionId: String) {
        val activity = requireActivity() as? DIDCommService ?: return
        val context = requireContext()

        activity.fetchCredentials(connectionId) { newCredentials ->
            (context as? Activity)?.runOnUiThread {
                credentials.addAll(newCredentials)
                updateCredentialsList()
            }
        }
    }

    private fun toggleViewType() {
        val currentViewType = requireContext().config.didCommViewType
        val newViewType = if (currentViewType == VIEW_TYPE_LIST) VIEW_TYPE_GRID else VIEW_TYPE_LIST
        requireContext().config.didCommViewType = newViewType
//        updateListAdapter()
    }

    override fun refreshItems(callback: (() -> Unit)?) {
        // In a real implementation, you would fetch connections from storage/API
        // This is just a placeholder
        val mockConnections = listOf(
            DIDConnection(
                id = "conn1",
                name = "Government ID",
                did = "did:example:123",
                dateConnected = System.currentTimeMillis() - 86400000
            ),
            DIDConnection(
                id = "conn2",
                name = "University",
                did = "did:example:456",
                dateConnected = System.currentTimeMillis() - 172800000
            )
        )

        connections.clear()
        connections.addAll(mockConnections)

        // Similarly for credentials
        val mockCredentials = listOf(
            Credential(
                id = "cred1",
                issuer = "Government",
                type = "ID Card",
                claims = mapOf("Name" to "John Doe", "DOB" to "01/01/1990"),
                dateIssued = System.currentTimeMillis() - 86400000,
                connectionId = "conn1"
            ),
            Credential(
                id = "cred2",
                issuer = "University",
                type = "Degree",
                claims = mapOf("Degree" to "Computer Science", "Year" to "2020"),
                dateIssued = System.currentTimeMillis() - 172800000,
                connectionId = "conn2"
            )
        )

        credentials.clear()
        credentials.addAll(mockCredentials)

        requireActivity().runOnUiThread {
            updateConnectionsList()
            updateCredentialsList()
            callback?.invoke()
        }
    }

    private fun updateConnectionsList() {
        if (connections.isEmpty()) {
            didCommBinding.didcommFragment.fragmentPlaceholder.beVisible()
            didCommBinding.didcommFragment.fragmentList.beGone()
        } else {
            didCommBinding.didcommFragment.fragmentPlaceholder.beGone()
            didCommBinding.didcommFragment.fragmentList.beVisible()
//            updateListAdapter()
        }
    }

    private fun updateCredentialsList() {
        if (credentials.isEmpty()) {
            didCommBinding.credentialsPlaceholder.beVisible()
            didCommBinding.credentialsList.beGone()
        } else {
            didCommBinding.credentialsPlaceholder.beGone()
            didCommBinding.credentialsList.beVisible()
            updateCredentialsAdapter()
        }
    }

    private fun updateCredentialsAdapter() {
        val currAdapter = didCommBinding.credentialsList.adapter as? CredentialsAdapter
        if (currAdapter == null) {
            CredentialsAdapter(
                credentials = credentials,
                recyclerView = didCommBinding.credentialsList, // Pass the credentials list here
                activity = requireActivity() as SimpleActivity,
                onCredentialClick = TODO()
            ).apply { 
                didCommBinding.credentialsList.adapter = this
            }
//            CredentialsAdapter(
//                activity = requireActivity() as SimpleActivity,
//                credentials = credentials, // Pass the credentials list here
//                recyclerView = didCommBinding.credentialsList
//            ).apply {
//                didCommBinding.credentialsList.adapter = this
//            }
        } else {
            currAdapter.updateItems(credentials)
        }
    }

    private fun showConnectionDetails(connection: DIDConnection) {
        // Implement connection details dialog
        activity?.showInfoDialog(
            title = connection.name,
            message = "DID: ${connection.did}\nConnected: ${connection}",
//            positive = R.string.ok
        )
    }


    private fun showCredentialDetails(credential: Credential) {
        val claimsText = credential.claims.entries.joinToString("\n") { "${it.key}: ${it.value}" }
        activity?.showInfoDialog(
            title = "${credential.issuer} - ${credential.type}",
            message = claimsText,
//            positive = R.string.ok
        )
    }

    private fun setViewType(viewType: Int, size: Int = 0) {
        val spanCount = context?.config?.didCommGridColumnCount ?: context?.config?.didCommGridColumnCount

        val layoutManager = if (viewType == VIEW_TYPE_GRID) {
            didCommBinding.didcommFragment.letterFastscroller.beGone()
            context?.let { MyGridLayoutManager(it, spanCount ?: 2) }
        } else {
            didCommBinding.didcommFragment.letterFastscroller.beGone()
            context?.let { MyLinearLayoutManager(it) }
        }
        didCommBinding.didcommFragment.fragmentList.layoutManager = layoutManager
    }
}

