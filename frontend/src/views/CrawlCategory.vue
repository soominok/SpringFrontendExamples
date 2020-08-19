<template>
  <Layout>
    <template #menubar>
      <!-- v-btn은 버튼을 주는 것.
           padding, width로 간격을 정해줬음. -->
      <v-btn @click="start('society')" text color="black"
        style="padding: 10px; width: 90px;">사회</v-btn>
      <v-btn @click="start('politics')" text color="black"
        style="padding: 10px; width: 90px;">정치</v-btn>
      <v-btn @click="start('economic')" text color="black"
        style="padding: 10px; width: 90px;">경제</v-btn>
      <v-btn @click="start('foreign')" text color="black"
        style="padding: 10px; width: 90px;">국제</v-btn>
      <v-btn @click="start('culture')" text color="black"
        style="padding: 10px; width: 90px;">문화</v-btn>
      <v-btn @click="start('digital')" text color="black"
        style="padding: 10px; width: 90px;">IT</v-btn>
    </template>
    <template #content>
      <!-- v-simple 쓰기 위해서 vuetify를 install 한 것 -->
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">No.</th>
              <th class="text-left">제목</th>
            </tr>
          </thead>
          <tbody>
            <!-- 크롤링해서 얻어온 정보를 list로 No와 title을 뿌려주는 것 -->
            <tr v-for="list of lists" :key="list.title">
              <td style="color: gray">{{ list.newsNo }}</td>
              <!-- a 태그 걸었으니까 뉴스 템플릿 쓸 수 있는 것 (링크 연결 가능) -->
              <td><a @click="clickNews(list.newsNo)">{{ list.title }}</a></td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import { mapState } from 'vuex'

export default {
  components: { Layout },
  computed: {
    ...mapState({
      lists: state => state.lists
    })
  },
  methods: {
    clickNews (newsNo) {
      console.log('clickNews: ' + newsNo)
      this.$store.dispatch('crawlFindOne', newsNo)
    },
    start (category) {
      this.$store.dispatch('crawlFind', category)
    }
  }
}
</script>
