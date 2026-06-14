# AI Usage

## Filler Data

GPT-5.3-mini

- This is an assignment; we are team copycats, and we're making a hotel website in vue with ionic; currently we're
  working on a home page and an about us page; please create some filler text that we can use for our page and feel free
  to think of an interesting backstory for the hotel
- (Insert current homepage) Generate at least two text sections for this Homepage; make sure the content does not
  overlap with text sections typically featured on an About Us page; the pictures of the hotel have a pool and palm
  trees in them, match your text to such a theme.
- (Insert current about page) This is the About Page, please add sections to this as well and rewrite the current
  content to match the theme.

## Fix small issue

GPT-5.3-mini

- (pasted contents of booking components); Which lines define the color of UI elements, one of them causes an issue in
  dark mode.
- (paste console warning) Where does this error originate from.
- Implement the blur element function in a central location (e.g. router/index).

## Some UI Topics

GPT-5.3-mini

- The project currently uses a pagelayout component with a slot that holds the content of the views:(insert component)if
  I want to style the page (e.g. center content, add space between content and screenborder) is it best
  practice to do that in the pagelayout component?
- Is there a way to separate the hero/banner element from the page styling while keeping pagelayout universal for all
  pages? -> multiple slots
- Add a divider line between the title and subtitle (insert banner component)

## Confirmation Page

GPT-5.3-mini

- Multiple questions on how certain backend functions others wrote work (e.g. some of the functions in
  useGuestBookingStore that I needed)
- (insert createbooking function) Where can I see the entire response? I want to see if roomtypeextras is a part of the
  response.
- Please generate some directions for our hotel that's at Palmoria Bay Street 1/2/3; these directions are for the
  confirmation page after a booking.

GPT-5.4-Mini (Codex)

- How do I make the confirmationpage printable as an a4 page? (in codex -> immediately started generating)
- Change the print view to work with a scoped style element.
- Currently the email copycathotel@mail.com is being used in the contact section of the confirmation card, the footer
  and imprint; Please create a referencable variable for contact information and change the hard coded strings to the
  new variable in the affected sections; use best practices.
- Add an openstreetmap for the directions part of the confirmation page; feel free to generate a location for our
  fictional hotel address; for hard coded coordinates please use ./constants/hotelContacts.ts
