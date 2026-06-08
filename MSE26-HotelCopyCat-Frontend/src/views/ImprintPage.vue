<template>
  <PageLayout>
      <Section title="Imprint">
        <p><b>Address: </b>{{ HOTEL_CONTACT.address }}</p>
        <p><b>Tel.: </b>{{ HOTEL_CONTACT.phone }}</p>
        <p><b>E-Mail: </b>{{ HOTEL_CONTACT.email }}</p>
        <br />
        <p><b>Commercial Register number: </b>0000000000</p>
        <p><b>Commercial Register Court: </b>Palmoria Bay Court Palmoria Bay</p>
        <p><b>Regulatory authority: </b>Regulatory Authority of Palmoria Bay</p>
        <p><b>Membership: </b>Yes</p>
        <p><b>VAT Number: </b>0000000000</p>
      </Section>


  </PageLayout>
</template>

<script setup lang="ts">
import PageLayout from '@/components/layout/PageLayout.vue'
import Section from '@/components/common/section.vue'
import { HOTEL_CONTACT } from '@/constants/hotelContact'
</script>
