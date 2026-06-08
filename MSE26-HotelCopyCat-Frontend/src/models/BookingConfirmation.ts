import type { BookingResponse } from '@/models/BookingResponse'

export interface BookingConfirmation {
  title: string
  message: string
  actionLabel: string
  actionRoute: string
  booking: BookingResponse
}
