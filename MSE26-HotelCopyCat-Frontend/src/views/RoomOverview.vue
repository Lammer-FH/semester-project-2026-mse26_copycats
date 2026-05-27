<template>
    <ion-page>
        <NavBar/>
        <ion-content>
            <div id="container">
                Room Overview
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
import NavBar from '@/components/navigation/NavBar.vue'
import {
  IonPage,
  IonContent
} from '@ionic/vue'
</script>