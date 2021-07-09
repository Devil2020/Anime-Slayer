package com.morse.animeslayer.ui.fragments.home

/*import androidx.fragment.app.setFragmentResultListener*/
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    private var animeAdapter : AnimeListAdapter ? = AnimeListAdapter(this)
    var binding: FragmentHomeBinding? = null
    var animeView: View ?= null
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
                if (binding?.currentAnime?.cardRoot?.visibility == View.VISIBLE) {
                    binding?.homeRoot?.let { it1 ->
                        animeView?.let { it2 ->
                            returnCardToOriginPosition(
                                it1,
                                binding?.currentAnime?.cardRoot!!,
                                it2,
                                450
                            )
                        }
                    }
                } else {
                    binding?.homeRoot?.let { it1 ->
                        binding?.currentAnime?.cardRoot?.let { it2 ->
                            animeView?.let { it3 ->
                                animateCard(
                                    it1,
                                    it2, it3
                                )
                            }
                        }
                    }
                }
            }
            R.id.searchExtebdedFab -> {

                binding?.homeRoot?.let { it1 ->
                    binding?.searchDialog?.let { it2 ->
                        binding?.searchExtebdedFab?.let { it3 ->
                            animateCard(
                                it1,
                                it2, it3
                            )
                        }
                    }
                }

            }
            R.id.closeSearch -> {

                binding?.homeRoot?.let { it1 ->
                    binding?.searchDialog?.let { it2 ->
                        binding?.searchExtebdedFab?.let { it3 ->
                            returnCardToOriginPosition(
                                it1,
                                it2,
                                it3,
                                400
                            )
                        }
                    }
                }

            }
            R.id.anime_detail_navigation -> {
/*                val extras =
                    FragmentNavigatorExtras(
                        Pair(binding.currentAnime.cardRoot, "container")
                    )*/
                findNavController().navigate(
                    R.id.action_homeFragment_to_animeDetailFragment,
                    null,
                    null,
                    // extras
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        itemDecorator = ItemOffsetDecoration(
            requireContext(),
            R.dimen._9sdp
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            this?.meIconIv?.setOnClickListener(
                homeClickListener
            )
            this?.searchIconIv?.setOnClickListener(
                homeClickListener
            )
            this?.currentAnime?.cardRoot?.setOnClickListener(
                homeClickListener
            )

            this?.searchExtebdedFab?.setOnClickListener(
                homeClickListener
            )

            this?.currentAnime?.animeDetailNavigation?.setOnClickListener(
                homeClickListener
            )

            this?.closeSearch?.setOnClickListener(
                homeClickListener
            )
            this?.animeListRv?.adapter = animeAdapter
            this?.animeListRv?.addItemDecoration(
                itemDecorator
            )
            this?.currentAnime?.cardRoot?.setOnClickListener {
                binding?.homeRoot?.let { it1 ->
                    binding?.currentAnime?.cardRoot?.let { it2 ->
                        animeView?.let { it3 ->
                            returnCardToOriginPosition(
                                it1,
                                it2,
                                it3,
                                650
                            )
                        }
                    }
                }
            }
            this?.animeListRv?.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY) {
                    this.searchExtebdedFab.shrink()
                } else {
                    this.searchExtebdedFab.extend()
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

/*        val extras = FragmentNavigatorExtras(
            Pair(animeImageView, "animeImage"),
            Pair(animeName, "animeName")
        )*/
        findNavController().navigateSafe(
            R.id.action_homeFragment_to_animeDetailFragment,
            //          navExtras = extras
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
        if (binding?.currentAnime?.cardRoot?.visibility == View.VISIBLE) {
            binding?.homeRoot?.let {
                returnCardToOriginPosition(
                    it,
                    binding?.currentAnime!!.cardRoot,
                    animeView,
                    450
                )
            }
        } else {
            binding?.homeRoot?.let { binding?.currentAnime?.cardRoot?.let { it1 ->
                animateCard(it,
                    it1, animeView)
            } }
            binding?.currentAnime?.render(anime.imageUrl!!, anime.title!!, anime.synopsis!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.animeListRv?.removeItemDecoration(
            itemDecorator
        )
        animeAdapter = null
        binding = null
        animeView = null
    }

}



