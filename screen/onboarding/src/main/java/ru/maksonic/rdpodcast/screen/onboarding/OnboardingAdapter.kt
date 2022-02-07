package ru.maksonic.rdpodcast.screen.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maksonic.rdpodcast.screen.onboarding.databinding.ItemOnboardingBinding

/**
 * @Author: maksonic on 05.02.2022
 */
class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.OnboardingListViewHolder>() {

    private val onboarding = listOf(
        OnboardingUi(
            0,
            "Добро пожаловать!",
            "Заряжайся, совершенствуйся и вдохновляйся.\nСотни аудиоподкастов от Игоря Войтенко",
            R.drawable.slide_first
        ),
        OnboardingUi(
            1,
            "Слушай в любое время",
            "Полный доступ к аудиоподкастам\nбез интернета 24/7",
            R.drawable.slide_second
        ),
        OnboardingUi(
            2,
            "Минималистичный дизайн",
            "Добавляй в избранное, ставь лайки\nи делись подкастами с кем хочешь!\nТолько самые необходимые функции.",
            R.drawable.slide_third
        ),
        OnboardingUi(
            3,
            "Road to the Dream\nПодкасты",
            "Создано для людей\nкоторые не боятся мечтать\nи хотят сделать наш Мир лучше!",
            R.drawable.slide_fourth
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingListViewHolder {
        return OnboardingListViewHolder(
            ItemOnboardingBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnboardingListViewHolder, position: Int) =
        holder.bind(onboarding[position])

    inner class OnboardingListViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnboardingUi) {
            with(binding) {
                title.text = item.title
                description.text = item.description
                image.setImageResource(item.image)
            }
        }
    }

    override fun getItemCount(): Int = onboarding.size
}