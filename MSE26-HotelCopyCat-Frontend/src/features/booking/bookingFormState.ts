import type { BookingForm } from '@/models/BookingForm'

export function createEmptyBookingForm(): BookingForm {
  return {
    firstName: '',
    lastName: '',
    email: '',
    confirmEmail: '',
    breakfast: false
  }
}
