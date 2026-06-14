<template>
  <ion-row>
    <ion-col class="pagination-controls">
      <ion-button fill="outline" :disabled="currentPage === 0" @click="$emit('previous')">
        <ion-icon slot="start" name="chevron-back-outline" />
        Previous
      </ion-button>

      <ion-text class="page-info">
        Page {{ currentPage + 1 }} / {{ totalPages }}
      </ion-text>

      <ion-button fill="outline" :disabled="currentPage >= totalPages - 1" @click="$emit('next')">
        Next
        <ion-icon slot="end" name="chevron-forward-outline" />
      </ion-button>
    </ion-col>
  </ion-row>
</template>

<script lang="ts">
import { IonRow, IonCol, IonButton, IonText, IonIcon } from '@ionic/vue'
import { chevronBackOutline, chevronForwardOutline } from 'ionicons/icons'
import { addIcons } from 'ionicons'

addIcons({ 'chevron-back-outline': chevronBackOutline, 'chevron-forward-outline': chevronForwardOutline })

export default {
  name: 'PaginationControls',

  components: {
    IonRow,
    IonCol,
    IonButton,
    IonText,
    IonIcon
  },

  props: {
    currentPage: {
      type: Number,
      required: true
    },
    totalPages: {
      type: Number,
      required: true
    }
  },

  emits: ['previous', 'next']
}
</script>

<style scoped>
.pagination-controls {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 1rem;
  padding: 1rem 0;
}

.page-info {
  min-width: 7rem;
  text-align: center;
  font-size: 0.95rem;
}
</style>
