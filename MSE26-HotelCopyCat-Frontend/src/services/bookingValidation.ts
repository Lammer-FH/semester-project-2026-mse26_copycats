import type { BookingForm } from '@/models/BookingForm'

export type BookingField = keyof BookingForm
export type BookingErrors = Partial<Record<BookingField, string>>
export type BookingTouchedState = Record<BookingField, boolean>

export function createEmptyBookingForm(): BookingForm {
  return {
    firstName: '',
    lastName: '',
    email: '',
    confirmEmail: '',
    breakfast: false
  }
}

export function createEmptyTouchedState(): BookingTouchedState {
  return {
    firstName: false,
    lastName: false,
    email: false,
    confirmEmail: false,
    breakfast: false
  }
}

export function normalizeEmail(email: string) {
  return email.trim().toLowerCase()
}

function isValidEmail(email: string) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
}

export function validateBookingField(form: BookingForm, field: BookingField): BookingErrors {
  const errors: BookingErrors = {}

  switch (field) {
    case 'firstName':
      if (!form.firstName.trim()) {
        errors.firstName = 'First name is required.'
      }
      break
    case 'lastName':
      if (!form.lastName.trim()) {
        errors.lastName = 'Last name is required.'
      }
      break
    case 'email':
      if (!form.email.trim()) {
        errors.email = 'Email address is required.'
      } else if (!isValidEmail(form.email)) {
        errors.email = 'Please enter a valid email address.'
      }
      break
    case 'confirmEmail':
      if (!form.confirmEmail.trim()) {
        errors.confirmEmail = 'Please confirm your email address.'
      } else if (normalizeEmail(form.confirmEmail) !== normalizeEmail(form.email)) {
        errors.confirmEmail = 'The email addresses do not match.'
      }
      break
    case 'breakfast':
      break
  }

  return errors
}

export function validateBookingForm(form: BookingForm): BookingErrors {
  return {
    ...validateBookingField(form, 'firstName'),
    ...validateBookingField(form, 'lastName'),
    ...validateBookingField(form, 'email'),
    ...validateBookingField(form, 'confirmEmail')
  }
}
