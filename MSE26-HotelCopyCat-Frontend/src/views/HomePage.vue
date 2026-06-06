<template>

  <PageLayout>

    <ion-row>
      <BannerSection image="/images/banner.jpg"
                     title="Hotel CopyCats"
                     subtitle="Find your perfect room"/>
    </ion-row>
    <ion-row>
      <Section title="Your Hotel in Aurora Haven">
        <p>A place where nature meets comfort, and every stay feels like a story worth remembering.</p>
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