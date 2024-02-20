<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.title"
        placeholder="模糊查询"
        style="width: 200px; margin-left: 0.5rem;"
        class="filter-item"
      />
      <div class="block" style="float: left;">
        <el-date-picker
          v-model="listQuery.searchDate"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </div>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" style="margin-left: 0.5rem;" @click="handleFilter">
        搜索
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      :default-sort="{prop: 'id', order: 'descending'}"
    >
      <el-table-column
        label="ID"
        prop="id"
        sortable
        align="center"
        min-width="15px"
      >
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="日期" min-width="40px" align="center" sortable prop="timestamp">
        <template slot-scope="{row}">
          <span>{{ row.createDate | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="卡号" min-width="100px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.chargeCardNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="卡密" min-width="100px">
        <template slot-scope="{row}">
          <span>{{ row.chargeCardSecret }}</span>
        </template>
      </el-table-column>
      <el-table-column label="充值类型" min-width="100px">
        <template slot-scope="{row}">
          <span>{{ row.chargeType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="充值账户" min-width="50px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.chargeAccount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="二维码图片(点击查看全部)" min-width="110px" align="center">
        <template slot-scope="{row}">
          <div class="demo-image__preview">
            <el-image
              style="width: 2.5rem; height: 2.5rem"
              :src="row.fileList[0]"
              :preview-src-list="row.fileList"
            />
          </div>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { fetchList } from '@/api/charge/article'
import Pagination from '@/components/Pagination'
import { parseTime } from '@/utils' // secondary package based on el-pagination

export default {
  name: 'ChargeTable',
  components: { Pagination },
  directives: { },
  filters: {},
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        title: undefined,
        searchDate: undefined
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      const fileList = [
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ]
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        for (const listKey in this.list) {
          console.log(this.list[listKey])
          this.list[listKey].fileList = fileList
          this.list.timestamp = parseTime(this.list.timestamp, '{y}-{m}-{d} {h}:{i}')
        }

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    }
  }
}
</script>
