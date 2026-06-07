<template>
  <ion-card class="booking-card">
    <ion-card-header>
      <ion-card-subtitle>Guest details</ion-card-subtitle>
      <ion-card-title>Enter booking information</ion-card-title>
    </ion-card-header>

    <ion-card-content>
      <p class="required-hint">* Required field</p>

      <ion-list lines="none" class="booking-form">
        <ion-item class="field" :class="{ 'field--invalid': !!errors.firstName }">
          <ion-input
            :value="form.firstName"
            label="First name *"
            label-placement="stacked"
            placeholder="Max"
            autocomplete="given-name"
            required
            :aria-invalid="!!errors.firstName"
            @ionInput="emitFieldUpdate('firstName', $event)"
            @ionBlur="emitFieldValidationRequest('firstName')"
          />
        </ion-item>
        <ion-note v-if="errors.firstName" class="field-error" color="danger">{{ errors.firstName }}</ion-note>

        <ion-item class="field" :class="{ 'field--invalid': !!errors.lastName }">
          <ion-input
            :value="form.lastName"
            label="Last name *"
            label-placement="stacked"
            placeholder="Mustermann"
            autocomplete="family-name"
            required
            :aria-invalid="!!errors.lastName"
            @ionInput="emitFieldUpdate('lastName', $event)"
            @ionBlur="emitFieldValidationRequest('lastName')"
          />
        </ion-item>
        <ion-note v-if="errors.lastName" class="field-error" color="danger">{{ errors.lastName }}</ion-note>

        <ion-item class="field" :class="{ 'field--invalid': !!errors.email }">
          <ion-input
            :value="form.email"
            type="email"
            label="Email address *"
            label-placement="stacked"
            placeholder="max@example.com"
            autocomplete="email"
            required
            :aria-invalid="!!errors.email"
            @ionInput="emitFieldUpdate('email', $event)"
            @ionBlur="emitFieldValidationRequest('email')"
          />
        </ion-item>
        <ion-note v-if="errors.email" class="field-error" color="danger">{{ errors.email }}</ion-note>

        <ion-item class="field" :class="{ 'field--invalid': !!errors.confirmEmail }">
          <ion-input
            :value="form.confirmEmail"
            type="email"
            label="Confirm email address *"
            label-placement="stacked"
            placeholder="max@example.com"
            autocomplete="off"
            required
            :aria-invalid="!!errors.confirmEmail"
            @ionInput="emitFieldUpdate('confirmEmail', $event)"
            @ionBlur="emitFieldValidationRequest('confirmEmail')"
          />
        </ion-item>
        <ion-note v-if="errors.confirmEmail" class="field-error" color="danger">{{ errors.confirmEmail }}</ion-note>

        <ion-item class="field toggle-field">
          <ion-toggle
            :checked="form.breakfast"
            justify="space-between"
            @ionChange="emitToggleUpdate($event)"
          >
            Breakfast
          </ion-toggle>
        </ion-item>
      </ion-list>

      <ion-button expand="block" class="action-button" @click="$emit('review-booking')">
        Review booking
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
  IonList,
  IonItem,
  IonInput,
  IonToggle,
  IonNote,
  IonButton
} from '@ionic/vue'
import type { InputCustomEvent, InputInputEventDetail } from '@ionic/vue'
import type { PropType } from 'vue'
import type { BookingForm } from '@/models/BookingForm'

export default {
  name: 'BookingGuestForm',

  components: {
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonCardContent,
    IonList,
    IonItem,
    IonInput,
    IonToggle,
    IonNote,
    IonButton
  },

  props: {
    form: {
      type: Object as PropType<BookingForm>,
      required: true
    },
    errors: {
      type: Object as PropType<Record<string, string>>,
      required: true
    }
  },

  emits: ['update-booking-field', 'validate-booking-field', 'review-booking'],

  methods: {
    emitFieldUpdate(field: string, event: InputCustomEvent<InputInputEventDetail>) {
      this.$emit('update-booking-field', {
        field,
        value: event.detail.value ?? ''
      })
    },

    emitFieldValidationRequest(field: string) {
      this.$emit('validate-booking-field', field)
    },

    emitToggleUpdate(event: CustomEvent) {
      this.$emit('update-booking-field', {
        field: 'breakfast',
        value: Boolean(event.detail.checked)
      })
    }
  }
}
</script>

<style scoped>
.booking-card {
  margin: 0;
  border-radius: 16px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}

.booking-form {
  background: transparent;
}

.required-hint {
  margin: 0 0 16px;
  color: var(--ion-color-medium);
  font-size: 0.9rem;
}

.field {
  --background: var(--ion-color-light);
  --border-radius: 12px;
  --padding-start: 14px;
  --inner-padding-end: 14px;
  margin-bottom: 12px;
  border-radius: 12px;
}

.field--invalid {
  --background: rgba(var(--ion-color-danger-rgb), 0.06);
  --border-color: rgba(var(--ion-color-danger-rgb), 0.45);
}

.field-error {
  display: block;
  margin: -4px 0 12px 14px;
  font-size: 0.88rem;
}

.toggle-field {
  margin-top: 8px;
}

.action-button {
  margin-top: 16px;
}
</style>
