import type { BookingConfirmation } from '@/models/BookingConfirmation'
import type { BookingForm } from '@/models/BookingForm'
import type { BookingRequest } from '@/models/BookingRequest'
import type { BookingResponse } from '@/models/BookingResponse'
import type { BookingRoomContext } from '@/models/BookingRoomContext'
import { normalizeEmail } from '@/features/booking/bookingValidation'

interface BookingPeriodPayload {
  checkIn: string
  checkOut: string
}

export function toBookingRoomContext(room: { id: number, name: string, description: string }): BookingRoomContext {
  return {
    id: room.id,
    name: room.name,
    description: room.description
  }
}

export function toBookingRequest(
  room: BookingRoomContext,
  form: BookingForm,
  period: BookingPeriodPayload
): BookingRequest {
  return {
    roomTypeId: room.id,
    checkIn: period.checkIn,
    checkOut: period.checkOut,
    firstName: form.firstName,
    lastName: form.lastName,
    email: normalizeEmail(form.email),
    confirmEmail: normalizeEmail(form.confirmEmail),
    breakfast: form.breakfast
  }
}

export function toBookingConfirmation(response: BookingResponse): BookingConfirmation {
  return {
    title: 'Booking submitted',
    message: 'Your booking request was submitted successfully.',
    actionLabel: 'Back to rooms',
    actionRoute: '/rooms',
    booking: response
  }
}
