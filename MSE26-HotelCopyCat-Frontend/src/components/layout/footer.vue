<template>
  <footer class="site-footer">
    <div class="footer-content">

      <div class="footer-section">
        <h3>{{ companyName }}</h3>
        <p>{{ address }}</p>
        <p>{{ email }}</p>
        <p>{{ phone }}</p>
      </div>

    </div>

    <div class="footer-bottom">
      © {{ currentYear }} {{ companyName }}
    </div>
  </footer>
</template>

<script lang="ts">
import { HOTEL_CONTACT } from '@/constants/hotelContact'

export default {
  name: 'SiteFooter',

  props: {
    companyName: {
      type: String,
      default: 'Hotel CopyCats'
    },
    address: {
      type: String,
      default: HOTEL_CONTACT.address
    },
    email: {
      type: String,
      default: HOTEL_CONTACT.email
    },
    phone: {
      type: String,
      default: HOTEL_CONTACT.phone
    }
  },

  computed: {
    currentYear() {
      return new Date().getFullYear()
    }
  }
}
</script>

<style scoped>
.site-footer {
  margin-top: 5rem;
  background: var(--ion-color-light);
  border-top: 1px solid var(--ion-color-medium-shade);
}

.footer-content {
  display: flex;
  flex-wrap: wrap;
  gap: 3rem;
  padding: 2rem;
  margin: 0 auto;
}

.footer-section {
  flex: 1;
  min-width: 220px;
}

.footer-section h3 {
  margin-top: 0;
  margin-bottom: 1rem;
}

.footer-section p {
  margin: 0.35rem 0;
}

.footer-section a {
  display: block;
  margin: 0.35rem 0;
  color: var(--ion-color-primary);
  text-decoration: none;
}

.footer-section a:hover {
  text-decoration: underline;
}

.footer-bottom {
  text-align: center;
  padding: 1rem;
  border-top: 1px solid var(--ion-color-medium-shade);
  font-size: 0.9rem;
  color: var(--ion-color-medium);
}
</style>
