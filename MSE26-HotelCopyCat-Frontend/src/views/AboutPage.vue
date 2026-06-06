<template>
  <PageLayout>

    <ion-row>
      <BannerSection image="/images/banner.jpg"
                     title="Get to know us"
                     subtitle="How it all began"/>
    </ion-row>
    <ion-row>
      <Section title="History">
        <p>Nestled between the calm waters of a hidden alpine lake and the northern edge of a centuries-old forest,
            The Aurora Haven began as a quiet retreat built in the early 1900s by a traveling architect who fell in
            love with the region's untouched beauty.</p>
        <p>Originally designed as a private guesthouse for artists and explorers, the building became known for
            something unexpected: guests often reported seeing faint auroras shimmering across the sky, even outside
            of typical northern lights seasons. Whether myth or rare atmospheric phenomenon, the name "Aurora Haven"
            stuck.</p>
      </Section>
    </ion-row>

  </PageLayout>
</template>

<script setup lang="ts">
import { IonRow } from '@ionic/vue'
import PageLayout from '@/components/layout/PageLayout.vue'
import BannerSection from '@/components/common/banner.vue'
import Section from '@/components/common/section.vue'
</script>
