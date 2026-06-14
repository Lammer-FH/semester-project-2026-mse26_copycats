<template>
  <ion-menu content-id="main-content" menu-id="nav-menu">

    <ion-header>
      <ion-toolbar>
        <ion-title>Boutique Hotel</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :scroll-y="false">

      <ion-list>

        <ion-menu-toggle :auto-hide="true">
          <ion-item router-link="/home">
            Home
          </ion-item>
        </ion-menu-toggle>

        <ion-menu-toggle :auto-hide="true">
          <ion-item router-link="/rooms">
            Rooms
          </ion-item>
        </ion-menu-toggle>

        <ion-menu-toggle :auto-hide="true">
          <ion-item router-link="/about">
            About
          </ion-item>
        </ion-menu-toggle>

        <ion-menu-toggle :auto-hide="true">
          <ion-item router-link="/imprint">
            Imprint
          </ion-item>
        </ion-menu-toggle>

      </ion-list>

    </ion-content>

  </ion-menu>
</template>

<script setup lang="ts">
import {
  IonMenu,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonList,
  IonItem,
  IonMenuToggle
} from '@ionic/vue'
</script>