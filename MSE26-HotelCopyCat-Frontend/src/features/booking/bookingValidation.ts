import type { BookingErrors, BookingField, BookingForm } from '@/models/BookingForm'

export function normalizeEmail(email: string) {
  return email.trim().toLowerCase()
}

function isValidEmail(email: string) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
}

function createFieldError(field: BookingField, message: string): BookingErrors {
  return { [field]: message }
}

function validateRequiredTextField(value: string, field: BookingField, message: string): BookingErrors {
  if (!value.trim()) {
    return createFieldError(field, message)
  }

  return {}
}

function validateEmailField(email: string): BookingErrors {
  const missingEmailError = validateRequiredTextField(email, 'email', 'Email address is required.')
  if (missingEmailError.email) {
    return missingEmailError
  }

  if (!isValidEmail(email)) {
    return createFieldError('email', 'Please enter a valid email address.')
  }

  return {}
}

function validateConfirmEmailField(form: BookingForm): BookingErrors {
  const missingConfirmEmailError = validateRequiredTextField(
    form.confirmEmail,
    'confirmEmail',
    'Please confirm your email address.'
  )
  if (missingConfirmEmailError.confirmEmail) {
    return missingConfirmEmailError
  }

  if (normalizeEmail(form.confirmEmail) !== normalizeEmail(form.email)) {
    return createFieldError('confirmEmail', 'The email addresses do not match.')
  }

  return {}
}

function validateFirstNameField(form: BookingForm): BookingErrors {
  return validateRequiredTextField(form.firstName, 'firstName', 'First name is required.')
}

function validateLastNameField(form: BookingForm): BookingErrors {
  return validateRequiredTextField(form.lastName, 'lastName', 'Last name is required.')
}

function validateBookingField(form: BookingForm, field: BookingField): BookingErrors {
  switch (field) {
    case 'firstName':
      return validateFirstNameField(form)
    case 'lastName':
      return validateLastNameField(form)
    case 'email':
      return validateEmailField(form.email)
    case 'confirmEmail':
      return validateConfirmEmailField(form)
    case 'breakfast':
      return {}
  }

  return {}
}

export function validateBookingForm(form: BookingForm): BookingErrors {
  return {
    ...validateBookingField(form, 'firstName'),
    ...validateBookingField(form, 'lastName'),
    ...validateBookingField(form, 'email'),
    ...validateBookingField(form, 'confirmEmail')
  }
}
