import { ref } from 'vue'
import type { BookingErrors, BookingField } from '@/models/BookingForm'
import type { BookingResponse } from '@/models/BookingResponse'
import type { BookingRoomContext } from '@/models/BookingRoomContext'
import bookingService from '@/services/bookingService'
import { createEmptyBookingForm } from '@/features/booking/bookingFormState'
import { toBookingRequest } from '@/features/booking/bookingMapper'
import { validateBookingForm } from '@/features/booking/bookingValidation'

interface BookingPeriodPayload {
  checkIn: string
  checkOut: string
}

export function useBookingForm() {
  const form = ref(createEmptyBookingForm())
  const errors = ref({} as BookingErrors)
  const submitError = ref('')
  const submitting = ref(false)

  function resetBookingForm() {
    form.value = createEmptyBookingForm()
    errors.value = {}
    submitError.value = ''
    submitting.value = false
  }

  function updateBookingField(payload: {
    field: BookingField
    value: string | boolean
  }) {
    form.value[payload.field] = payload.value as never
    submitError.value = ''
  }

  function validateForm() {
    errors.value = validateBookingForm(form.value)
    return Object.keys(errors.value).length === 0
  }

  async function submitBooking(
    room: BookingRoomContext,
    period: BookingPeriodPayload
  ): Promise<BookingResponse | null> {
    if (!validateForm()) {
      return null
    }

    submitError.value = ''
    submitting.value = true

    try {
      return await bookingService.createBooking(
        toBookingRequest(room, form.value, period)
      )
    } catch (error) {
      submitError.value = error instanceof Error
        ? error.message
        : 'Booking could not be completed. Please try again.'
      return null
    } finally {
      submitting.value = false
    }
  }

  return {
    form,
    errors,
    submitError,
    submitting,
    resetBookingForm,
    updateBookingField,
    validateForm,
    submitBooking
  }
}
