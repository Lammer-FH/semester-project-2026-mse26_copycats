<template>
  <PageLayout>

    <template #banner>
      <BannerSection image="/images/AboutUsBannerImg.png"
                     title="Get to know us"
                     subtitle="How it all began"/>
    </template>

    <ion-row>
      <Section title="History">
        <p>CopyCats Hotel began as a quiet seaside retreat along a sheltered tropical coast,
          where white sand meets calm, turquoise waters. In the early 1900s, a traveling architect
          discovered the untouched shoreline and envisioned a place built entirely around light,
          warmth, and simplicity.</p>
        <p>Originally designed as a private hideaway for artists, writers, and travelers seeking inspiration,
          the property quickly became known for its peaceful atmosphere, ocean sunsets, and the feeling
          that time moves just a little slower here. Over the decades, it evolved into the boutique hotel
          it is today while preserving its relaxed coastal spirit.</p>
      </Section>
    </ion-row>

    <ion-row>
      <Section title="Our Philosophy">
        <p>
          At CopyCats Hotel, we believe true comfort comes from simplicity, space, and natural rhythm.
          Everything we design is centered around ease — soft mornings, warm light, and a sense of calm
          that stays with you throughout your visit.
        </p>
        <p>
          From open-air lounges to shaded palm gardens and a serene poolside atmosphere, our goal is to
          create a place where guests can fully relax, unwind, and feel at home in a coastal paradise.
        </p>
      </Section>
    </ion-row>

    <ion-row>
      <Section title="Your Stay by the Sea">
        <p>
          Surrounded by swaying palm trees and the sound of gentle waves, CopyCats Hotel offers a
          peaceful escape from everyday life.
        </p>
        <p>
          Spend your days relaxing by the pool, enjoying long beach walks, or watching the sunset paint
          the sky in soft tropical colors. Every detail is designed to help you slow down and enjoy the moment.
        </p>
      </Section>
    </ion-row>

  </PageLayout>
</template>

<script setup lang="ts">
import {IonRow} from '@ionic/vue'
import PageLayout from '@/components/layout/PageLayout.vue'
import BannerSection from '@/components/layout/banner.vue'
import Section from '@/components/layout/section.vue'
</script>
