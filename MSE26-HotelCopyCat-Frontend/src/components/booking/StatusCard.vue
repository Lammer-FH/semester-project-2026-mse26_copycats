<template>
  <ion-card :class="['status-card', `status-card--${variant}`]">
    <ion-card-header>
      <ion-card-subtitle v-if="subtitle">
        {{ subtitle }}
      </ion-card-subtitle>
      <ion-card-title>
        {{ title }}
      </ion-card-title>
    </ion-card-header>

    <ion-card-content>
      <div class="status-message" :role="ariaRole">
        <slot>
          <p>{{ message }}</p>
        </slot>
      </div>
      
      <slot name="bookingDetails"/>

      <ion-button v-if="actionLabel" expand="block" class="action-button" :router-link="actionRoute">
        {{ actionLabel }}
      </ion-button>
    </ion-card-content>
  </ion-card>
</template>

<script lang="ts">
import {
  IonCard,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonCardContent,
  IonButton
} from '@ionic/vue'

export default {
  name: 'StatusCard',

  components: {
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonCardContent,
    IonButton
  },

  props: {
    variant: {
      type: String,
      default: 'info'
    },
    subtitle: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      required: true
    },
    message: {
      type: String,
      default: ''
    },
    actionLabel: {
      type: String,
      default: ''
    },
    actionRoute: {
      type: String,
      default: ''
    }
  },

  computed: {
    ariaRole(): string {
      return this.variant === 'success' ? 'status' : 'alert'
    }
  }
}
</script>

<style scoped>
.status-card {
  margin: 0;
  border-radius: 16px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}

.status-message {
  padding: 14px 16px;
  border-radius: 12px;
  line-height: 1.6;
}

.status-message p {
  margin: 0;
}

.status-card--error .status-message {
  border: 1px solid rgba(var(--ion-color-danger-rgb), 0.28);
  background: rgba(var(--ion-color-danger-rgb), 0.08);
  color: var(--ion-color-danger);
}

.status-card--success .status-message {
  border: 1px solid rgba(var(--ion-color-success-rgb), 0.28);
  background: rgba(var(--ion-color-success-rgb), 0.08);
  color: var(--ion-color-success);
}

.status-card--info .status-message {
  border: 1px solid rgba(var(--ion-color-primary-rgb), 0.2);
  background: rgba(var(--ion-color-primary-rgb), 0.08);
  color: var(--ion-color-primary);
}

.action-button {
  margin-top: 18px;
}
</style>
