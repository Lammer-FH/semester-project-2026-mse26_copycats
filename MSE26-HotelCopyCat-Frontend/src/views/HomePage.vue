<template>

  <ion-page>

    <NavBar />
    
    <ion-content>

      <div id="container">
        Home Page
      </div>

    </ion-content>

  </ion-page>

</template>

<script setup lang="ts">
import {
  IonPage,
  IonContent
} from '@ionic/vue'

import NavBar from '@/components/navigation/NavBar.vue'
</script>