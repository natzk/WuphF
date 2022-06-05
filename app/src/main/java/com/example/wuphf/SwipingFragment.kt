package com.example.wuphf

import RetrofitHelper
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



/**
 * A simple [Fragment] subclass.
 * Use the [SwipingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SwipingFragment : Fragment(), CardStackListener {

    private lateinit var cardStackView : CardStackView
    val manager by lazy { CardStackLayoutManager(context, this) }
    private lateinit var adapter : CardStackAdapter

    val pos : CardStackAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_swiping, container, false)
        cardStackView = v.findViewById(R.id.card_stack_view)
//        setupCardStackView()
        val dogsAPI = RetrofitHelper.getInstance().create(DogService::class.java)
        GlobalScope.launch { val result = dogsAPI.getAllDogs()

            if (result!=null) {
                Log.d("result", "result is not null" + result.body().toString())

                activity?.runOnUiThread(java.lang.Runnable {
                    var allDogsResult : List<String> = result.body()?.message!!
                    allDogsResult = allDogsResult.asSequence().shuffled().take(allDogsResult.size).toList()
                    adapter = CardStackAdapter(allDogsResult)
                    setupCardStackView()
                })

            }
        }

        return v
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SwipingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {

        if(direction.toString() == "Right"){

            Toast.makeText(context, direction.toString(), Toast.LENGTH_SHORT).show()
        }

        else if(direction.toString() == "Left"){

        }
    }


    override fun onCardRewound() {

    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

}