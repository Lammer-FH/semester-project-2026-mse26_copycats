export interface BookingForm {
  firstName: string
  lastName: string
  email: string
  confirmEmail: string
  breakfast: boolean
}

export type BookingField = keyof BookingForm
export type BookingErrors = Partial<Record<BookingField, string>>
