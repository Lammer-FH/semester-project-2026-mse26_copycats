<template>

  <PageLayout>

    <template #banner>
      <BannerSection image="/images/HomePageBannerImg.png"
                     title="Hotel CopyCats"
                     subtitle="Find your perfect room"/>
    </template>

    <ion-row>
      <Section title="Your Hotel in Aurora Haven">
        <p>A place where warm breezes, sunlit spaces, and gentle comfort come together to create the perfect escape.</p>
        <p>Step into a relaxed tropical retreat surrounded by palm trees, crystal-clear water, and endless summer vibes.
          Whether you're here to unwind by the pool or enjoy slow, sunny days, Aurora Haven is your place to feel at
          ease.</p>
      </Section>
    </ion-row>

    <ion-row>
      <Section title="Relax in our tropical pool area">
        <p>Step into a sun-soaked oasis surrounded by palm trees and calm blue waters.
          Our outdoor pool area is designed for pure relaxation — whether you’re taking
          a refreshing morning swim or unwinding under the warm afternoon sun.</p>
        <p>Comfortable loungers, shaded cabanas, and poolside service make it easy to
          slow down and enjoy every moment of your stay. This is where vacation mode
          truly begins.</p>
      </Section>
    </ion-row>

    <ion-row>
      <Section title="Everything you need for the perfect stay">
        <p>At Hotel CopyCats, comfort comes first. Our rooms are designed with a modern,
          airy style inspired by tropical surroundings — bright spaces, natural textures,
          and views that bring the outside in.</p>
        <p>Enjoy high-speed Wi-Fi, a relaxing lounge area, and easy access to all hotel
          amenities including the pool, breakfast terrace, and 24/7 reception service.
          Whether you're here for a short getaway or a longer escape, everything is set
          up to make your stay effortless.</p>
      </Section>
    </ion-row>

  </PageLayout>

</template>

<script setup lang="ts">
import {IonRow} from '@ionic/vue'
import PageLayout from '@/components/layout/PageLayout.vue'
import BannerSection from '@/components/common/banner.vue'
import Section from '@/components/common/section.vue'
</script>