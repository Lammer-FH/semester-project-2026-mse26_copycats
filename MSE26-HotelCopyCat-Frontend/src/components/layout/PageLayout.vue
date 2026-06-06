<template>
  <ion-page>
    <NavBar />
    <ion-content>
      <ion-grid>
        <slot />
      </ion-grid>
      <SiteFooter />
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { IonPage, IonContent, IonGrid } from '@ionic/vue'
import NavBar from '@/components/navigation/NavBar.vue'
import SiteFooter from '@/components/common/footer.vue'

export default {
  name: 'PageLayout',

  components: {
    IonPage,
    IonContent,
    IonGrid,
    NavBar,
    SiteFooter
  }
}
</script>
