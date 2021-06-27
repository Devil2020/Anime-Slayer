package com.morse.animeslayer.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
/*import androidx.fragment.app.setFragmentResultListener*/
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentHomeBinding
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.menu.host.MenuBottomSheet
import com.morse.animeslayer.ui.fragments.menu.pages.menu.MenuFragment
import com.morse.animeslayer.ui.fragments.menu.pages.menu.MenuType
import com.morse.animeslayer.utils.render
import com.morse.common.extensions.navigateSafe
import com.morse.common.extensions.navigateSafeWithNavDirections
import com.morse.common.utils.ItemOffsetDecoration


class HomeFragment : Fragment(), AnimeListListener {

    private val animeAdapter = AnimeListAdapter(this)
    val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    lateinit var animeView: View

    var isSearchClicked = false

    lateinit var itemDecorator: ItemOffsetDecoration

    private val homeClickListener = View.OnClickListener {
        when (it.id) {
            R.id.me_icon_iv -> {
                it.startAnimation(
                    AnimationUtils.loadAnimation(requireContext(), R.anim.scale_click)
                )
                MenuBottomSheet().show(parentFragmentManager, MenuBottomSheet.TAG)
            }
            R.id.search_icon_iv -> {

            }
            R.id.cardOfAnimeDescribtion -> {
                if (binding.currentAnime.cardRoot.visibility == View.VISIBLE) {
                    returnCardToOriginPosition(
                        binding.homeRoot,
                        binding.currentAnime.cardRoot,
                        animeView,
                        450
                    )
                } else {
                    animateCard(binding.homeRoot, binding.currentAnime.cardRoot, animeView)
                }
            }
            R.id.searchExtebdedFab -> {

                animateCard(binding.homeRoot, binding.searchDialog, binding.searchExtebdedFab)

            }

            R.id.closeSearch -> {

                returnCardToOriginPosition(
                    binding.homeRoot,
                    binding.searchDialog,
                    binding.searchExtebdedFab,
                    400
                )

            }

            R.id.anime_detail_navigation -> {
                val extras =
                    FragmentNavigatorExtras(
                        Pair(binding.currentAnime.animeImageview, "animeImage"),
                        Pair(binding.currentAnime.animeName, "animeName")
                    )
                findNavController().navigate(
                    R.id.action_homeFragment_to_animeDetailFragment,
                    null, null,
                    extras
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        itemDecorator = ItemOffsetDecoration(
            requireContext(),
            R.dimen._9sdp
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            meIconIv.setOnClickListener(
                homeClickListener
            )
            searchIconIv.setOnClickListener(
                homeClickListener
            )
            currentAnime.cardRoot.setOnClickListener(
                homeClickListener
            )

            searchExtebdedFab.setOnClickListener(
                homeClickListener
            )

            currentAnime.animeDetailNavigation.setOnClickListener(
                homeClickListener
            )

            closeSearch.setOnClickListener(
                homeClickListener
            )
            animeListRv.adapter = animeAdapter
            animeListRv.addItemDecoration(
                itemDecorator
            )
            currentAnime.cardRoot.setOnClickListener {
                returnCardToOriginPosition(
                    binding.homeRoot,
                    binding.currentAnime.cardRoot,
                    animeView,
                    650
                )
            }
            animeListRv.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY) {
                    searchExtebdedFab.shrink()
                } else {
                    searchExtebdedFab?.extend()
                }
            }
        }
        listenToActions()
    }

    override fun onAnimeLongClicked(
        animeImageView: ImageView,
        animeName: TextView,
        anime: AnimeListResponse.Anime
    ) {

        val extras = FragmentNavigatorExtras(
            Pair(animeImageView, "animeImage"),
            Pair(animeName, "animeName")
        )
        findNavController().navigateSafe(
            R.id.action_homeFragment_to_animeDetailFragment,
            navExtras = extras
        )
    }

    private fun listenToActions() {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            MenuFragment::class.java.name,
            viewLifecycleOwner
        ) { requestKey: String, dataSended: Bundle ->
            val menuItem = dataSended.getString(MenuType::class.java.name) ?: "Current_Season"
            when (MenuType.valueOf(menuItem)) {

                MenuType.Current_Season -> {
                    Toast.makeText(requireContext(), "Current_Season", Toast.LENGTH_SHORT).show()
                }
                MenuType.Schedule -> {
                    Toast.makeText(requireContext(), "Schedule", Toast.LENGTH_SHORT).show()
                }
                MenuType.Favourite -> {
                    findNavController().navigateSafeWithNavDirections(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
                }
                MenuType.Top_Tv -> {
                    Toast.makeText(requireContext(), "Top_Tv", Toast.LENGTH_SHORT).show()
                }
                MenuType.Top_Airing -> {
                    Toast.makeText(requireContext(), "Top_Airing", Toast.LENGTH_SHORT).show()
                }
                MenuType.Top_Movie -> {
                    Toast.makeText(requireContext(), "Top_Movie", Toast.LENGTH_SHORT).show()
                }
                MenuType.Top_Incoming -> {
                    Toast.makeText(requireContext(), "Top_Incoming", Toast.LENGTH_SHORT).show()
                }
                MenuType.Top_Manga -> {
                    Toast.makeText(requireContext(), "Top Manga", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onAnimeClicked(animeView: View, anime: AnimeListResponse.Anime) {
        this.animeView = animeView
        if (binding.currentAnime.cardRoot.visibility == View.VISIBLE) {
            returnCardToOriginPosition(
                binding.homeRoot,
                binding.currentAnime.cardRoot,
                animeView,
                450
            )
        } else {
            animateCard(binding.homeRoot, binding.currentAnime.cardRoot, animeView)
            binding.currentAnime.render(anime.imageUrl!!, anime.title!!, anime.synopsis!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.animeListRv.removeItemDecoration(
            itemDecorator
        )
    }

}



