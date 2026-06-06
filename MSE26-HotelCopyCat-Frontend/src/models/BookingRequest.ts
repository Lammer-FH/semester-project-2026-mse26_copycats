export interface BookingRequest {
  roomTypeId: number
  checkIn: string
  checkOut: string
  firstName: string
  lastName: string
  email: string
  confirmEmail: string
  breakfast: boolean
}
