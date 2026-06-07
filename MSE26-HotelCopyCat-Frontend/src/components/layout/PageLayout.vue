<template>
  <ion-page>
    <NavBar/>
    <ion-content>
      <ion-grid>
        <slot name="banner"/>

        <PageContentContainer>
          <slot />
        </PageContentContainer>

      </ion-grid>
      <SiteFooter/>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {IonPage, IonContent, IonGrid} from '@ionic/vue'
import NavBar from '@/components/navigation/NavBar.vue'
import SiteFooter from '@/components/common/footer.vue'
import PageContentContainer from '@/components/layout/PageContentContainer.vue'

export default {
  name: 'PageLayout',

  components: {
    PageContentContainer,
    IonPage,
    IonContent,
    IonGrid,
    NavBar,
    SiteFooter
  }
}
</script>
