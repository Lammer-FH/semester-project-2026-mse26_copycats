<template>

  <PageLayout>

    <template #banner>
      <BannerSection image="/images/banner.jpg"
                     title="Hotel CopyCats"
                     subtitle="Find your perfect room"/>
    </template>

    <ion-row>
      <Section title="Your Hotel in Aurora Haven">
        <p>A place where nature meets comfort, and every stay feels like a story worth remembering.</p>
        <p>Escape into a world of quiet luxury surrounded by forests, mountains, and starlit skies. Whether you're here for adventure or relaxation, Aurora Haven is your sanctuary away from home.</p>
      </Section>
    </ion-row>

    <ion-row>
      <Section title="Your Hotel in Aurora Haven">
        <p>Nestled between the calm waters of a hidden alpine lake and the northern edge of a centuries-old forest,
          The Aurora Haven began as a quiet retreat built in the early 1900s by a traveling architect who fell in
          love with the region's untouched beauty.</p>
        <p>Escape into a world of quiet luxury surrounded by forests, mountains, and starlit skies. Whether you're here for adventure or relaxation, Aurora Haven is your sanctuary away from home.</p>
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