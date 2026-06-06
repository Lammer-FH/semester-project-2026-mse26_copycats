<template>
    <PageLayout>
        <ion-row>
          <h1>Imprint</h1>
        </ion-row>
        <ion-row>
          <div id="Imprint details">
            <p><b>Address: </b>AuroraHaven 1/2/3</p>
            <p><b>Tel.: </b>+43 (0) 0000000</p>
            <p><b>E-Mail: </b>help@temp.pain</p>
            <br>
            <p>
              Commercial Register number: Registered <br>
              Commercial Register Court: RealCourtTrust RealPlace<br>
              Regulatory authority: NotBribedFR RealPlaceTrust<br>
              Membership: Yes, No<br>
              VAT Number: Yup<br>
            </p>
          </div>
        </ion-row>
    </PageLayout>
</template>

<script setup lang="ts">
import { IonRow } from '@ionic/vue'
import PageLayout from '@/components/layout/PageLayout.vue'
</script>