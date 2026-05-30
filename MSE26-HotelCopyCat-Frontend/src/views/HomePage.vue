<template>

  <ion-page>

    <NavBar/>

    <ion-content>

      <ion-grid>
        <ion-row>
          <BannerSection image="/images/banner.jpg"
                         title="Hotel CopyCats"
                         subtitle="Find your perfect room"/>
        </ion-row>
        <ion-row>
          <Section title="Your Hotel in Aurora Haven"
                   text="A place where nature meets comfort, and every stay feels like a story worth remembering.

Escape into a world of quiet luxury surrounded by forests, mountains, and starlit skies. Whether you're here for adventure or relaxation, Aurora Haven is your sanctuary away from home."/>
        </ion-row>
      </ion-grid>
      <SiteFooter/>
    </ion-content>

  </ion-page>

</template>

<script setup lang="ts">
import {
  IonPage,
  IonContent
} from '@ionic/vue'

import NavBar from '@/components/navigation/NavBar.vue'
import BannerSection from "@/components/common/banner.vue";
import SiteFooter from "@/components/common/footer.vue";
import Section from "@/components/common/section.vue";
</script>