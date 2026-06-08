export const BOOKING_STEP = {
  FORM: 'form',
  REVIEW: 'review',
  CONFIRMATION: 'confirmation'
} as const

export type BookingStep = typeof BOOKING_STEP[keyof typeof BOOKING_STEP]
