<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="75px">
      <el-form-item label="模糊查询" prop="tableName">
        <el-input
          v-model="queryParams.filter"
          placeholder="请输入查询条件"
          clearable
          @keyup.enter.native="handleQuery"
          size="mini"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          size="mini"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="danger" icon="el-icon-delete" size="mini" @click="truncateData">数据清除</el-button>

      </el-form-item>
    </el-form>

    <!--    <el-row :gutter="10" class="mb8">-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="primary"-->
    <!--          plain-->
    <!--          icon="el-icon-download"-->
    <!--          size="mini"-->
    <!--          @click="handleGenTable"-->
    <!--          v-hasPermi="['tool:gen:code']"-->
    <!--        >生成</el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="info"-->
    <!--          plain-->
    <!--          icon="el-icon-upload"-->
    <!--          size="mini"-->
    <!--          @click="openImportTable"-->
    <!--          v-hasPermi="['tool:gen:import']"-->
    <!--        >导入</el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="success"-->
    <!--          plain-->
    <!--          icon="el-icon-edit"-->
    <!--          size="mini"-->
    <!--          :disabled="single"-->
    <!--          @click="handleEditTable"-->
    <!--          v-hasPermi="['tool:gen:edit']"-->
    <!--        >修改</el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="danger"-->
    <!--          plain-->
    <!--          icon="el-icon-delete"-->
    <!--          size="mini"-->
    <!--          :disabled="multiple"-->
    <!--          @click="handleDelete"-->
    <!--          v-hasPermi="['tool:gen:remove']"-->
    <!--        >删除</el-button>-->
    <!--      </el-col>-->
    <!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
    <!--    </el-row>-->

    <el-table v-loading="loading" :data="tableList">
      <el-table-column
        label="ID"
        align="center"
        prop="id"
        :show-overflow-tooltip="true"
        min-width="120"
        sortable
      />
      <el-table-column label="创建时间" align="center" prop="createDate" width="160" sortable/>
      <el-table-column
        label="卡号"
        align="center"
        prop="chargeCardNumber"
        :show-overflow-tooltip="true"
        min-width="120"
      />
      <el-table-column
        label="卡密"
        align="center"
        prop="chargeCardSecret"
        :show-overflow-tooltip="true"
        min-width="120"
        type="password"
      >
        <template slot-scope="{row}">
          <div class="demo-image__preview">
            <el-input
              placeholder="请输入内容"
              v-model="row.chargeCardSecret"
              :disabled="false"
              type="password"
              show-password
            >
            </el-input>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="充值类型"
        align="center"
        prop="chargeType"
        :show-overflow-tooltip="true"
        min-width="120"
      >
        <template slot-scope="{row}">
          <el-tag type="success" v-if="row.chargeType === 'xiechengchongzhi'">携程充值</el-tag>
          <el-tag v-if="row.chargeType === 'youkachongzhi'">油卡充值</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="充值账户"
        align="center"
        prop="chargeAccount"
        :show-overflow-tooltip="true"
        min-width="120"
      />
      <el-table-column
        label="二维码图片(点击查看全部)"
        align="center"
        :show-overflow-tooltip="true"
        min-width="120"
      >
        <template slot-scope="{row}">
          <div class="demo-image__preview">
            <el-image
              v-if="row.fileList[0] !== ''"
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
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>
<style>
@media (max-width: 768px) {
  .el-date-range-picker .el-picker-panel__body {
    min-width: 100%;
  }

  .el-date-range-picker__content {
    width: 100% !important;
  }

  .el-date-range-picker {
    width: 100% !important;
  }
}
</style>

<script>
import {fetchList, truncate} from '@/api/charge/article'

export default {
  name: 'recharge',
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 唯一标识符
      uniqueId: '',
      // 选中数组
      ids: [],
      // 选中表数组
      tableNames: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 表数据
      tableList: [],
      // 日期范围
      dateRange: '',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        filter: undefined
      }
    }
  },
  created() {
    this.getList()
  },
  activated() {
    const time = this.$route.query.t
    if (time != null && time != this.uniqueId) {
      this.uniqueId = time
      this.queryParams.pageNum = Number(this.$route.query.pageNum)
      this.getList()
    }
  },
  methods: {
    /** 查询表集合 */
    getList() {
      this.loading = true
      fetchList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.tableList = response.data.data
          this.total = response.data.total
          this.loading = false
        }
      )
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        filter: undefined
      }
      this.handleQuery()
    },
    truncateData() {
      this.$confirm('此操作将永久清空全部数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        truncate().then(response => {
            const code = response.code;
            if (code === 200) {
              this.$message({
                type: 'success',
                message: '清除成功!'
              });
              this.getList();
            } else {
              this.$message.error('错误！清除数据出现异常！');
            }
          }
        )
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
}
</script>
<style>
.el-image img {
  border: 1px solid red;
  border-radius: 5px;
}
</style>
