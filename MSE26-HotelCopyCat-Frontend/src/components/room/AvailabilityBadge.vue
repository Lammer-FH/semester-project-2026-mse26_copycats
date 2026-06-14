<template>
  <ion-badge :color="available ? 'success' : 'danger'">
    {{ available ? 'Available' : 'Unavailable' }}
  </ion-badge>
</template>

<script lang="ts">
import { IonBadge } from '@ionic/vue'

export default {
  name: 'AvailabilityBadge',

  components: { IonBadge },

  props: {
    available: {
      type: Boolean,
      required: true
    }
  }
}
</script>
