package ru.maksonic.rdpodcast.core.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import ru.maksonic.rdpodcast.core.R

/**
 * @Author: maksonic on 05.02.2022
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView(savedInstanceState)
        toolbarBackPressed()
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    private fun toolbarBackPressed() {
        view?.findViewById<Toolbar>(R.id.toolBar)
            ?.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}