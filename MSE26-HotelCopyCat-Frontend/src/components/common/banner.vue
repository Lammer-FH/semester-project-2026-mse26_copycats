<template>
  <div class="banner">
    <ion-img :src="image" class="banner-image"/>
    <div class="overlay">
      <div class="content">
        <h1 class="title">{{ title }}</h1>

        <div v-if="subtitle" class="divider"></div>

        <p v-if="subtitle" class="subtitle">
          {{ subtitle }}
        </p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {
  IonImg
} from '@ionic/vue'

export default {
  name: 'BannerSection',

  components: {
    IonImg
  },

  props: {
    image: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: true
    },
    subtitle: {
      type: String,
      default: ''
    }
  }
}
</script>

<style scoped>
.banner {
  position: relative;
  width: 100%;
  height: 320px;
  overflow: hidden;
}

.banner-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(
      to top,
      rgba(0, 0, 0, 0.65),
      rgba(0, 0, 0, 0.2)
  );

  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 16px;
}

.content {
  color: white;
  max-width: 600px;
}

.title {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 1.5rem;
  opacity: 0.9;
  margin-bottom: 16px;
}

.divider {
  width: 18rem;
  height: 2px;
  background: rgba(255, 255, 255, 0.85);
  margin: 0 auto;
}
</style>