<template>
  <div class="login-container">
    <div class="pic-tiaofu">
      <!--<img src="https://th.bing.com/th/id/OIP.bj79iwPGLX187fQu585d6gHaCz?w=328&h=132&c=7&r=0&o=5&dpr=2&pid=1.7" alt="条幅">-->
    </div>
    <div class="charge">
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="100px"
        style="width: 80%; margin:0 auto 0; padding: 1rem;"
      >
        <div class="chongzhibu" style="width: 800px;">
          <el-form-item label="充值类型" prop="chargeType" name="text_one">
            <el-radio v-model="temp.chargeType" label="youkachongzhi" class="youka" border>油卡充值</el-radio>
            <el-radio v-model="temp.chargeType" label="xiechengchongzhi" class="xiecheng" border>携程充值</el-radio>
          </el-form-item>
        </div>
        <el-form-item label="充值账户" prop="chargeAccount" name="text_one">
          <el-input v-model="temp.chargeAccount" class="input_one" />
        </el-form-item>
        <el-form-item label="卡号1" prop="chargeCardNumber1" name="text_one">
          <el-input v-model="temp.chargeCardNumber1" class="input_one" />
        </el-form-item>
        <el-form-item label="卡密1" prop="chargeCardSecret1" name="text_one">
          <el-input v-model="temp.chargeCardSecret1" type="password" show-password class="input_one" />
        </el-form-item>
        <el-form-item label="卡号2" prop="chargeCardNumber2" name="text_one">
          <el-input v-model="temp.chargeCardNumber2" class="input_one" />
        </el-form-item>
        <el-form-item label="卡密2" prop="chargeCardSecret2" name="text_one">
          <el-input v-model="temp.chargeCardSecret2" type="password" show-password class="input_one" />
        </el-form-item>
        <el-form-item label="卡号3" prop="chargeCardNumber3" name="text_one">
          <el-input v-model="temp.chargeCardNumber3" class="input_one" />
        </el-form-item>
        <el-form-item label="卡密3" prop="chargeCardSecret3" name="text_one">
          <el-input v-model="temp.chargeCardSecret3" type="password" show-password class="input_one" />
        </el-form-item>
        <el-form-item label="卡号4" prop="chargeCardNumber4" name="text_one">
          <el-input v-model="temp.chargeCardNumber4" class="input_one" />
        </el-form-item>
        <el-form-item label="卡密4" prop="chargeCardSecret4" name="text_one">
          <el-input v-model="temp.chargeCardSecret4" type="password" show-password class="input_one" />
        </el-form-item>
        <el-form-item label="卡号5" prop="chargeCardNumber5" name="text_one">
          <el-input v-model="temp.chargeCardNumber5" class="input_one" />
        </el-form-item>
        <el-form-item label="卡密5" prop="chargeCardSecret5" name="text_one">
          <el-input v-model="temp.chargeCardSecret5" type="password" show-password class="input_one" />
        </el-form-item>
        <el-form-item label="卡号6" prop="chargeCardNumber6" name="text_one">
          <el-input v-model="temp.chargeCardNumber6" class="input_one" />
        </el-form-item>
        <el-form-item label="卡密6" prop="chargeCardSecret6" name="text_one">
          <el-input v-model="temp.chargeCardSecret6" type="password" show-password class="input_one" />
        </el-form-item>
        <el-form-item label="快捷二维码识别图片上传" prop="fileList" label-width="175px" />
        <el-form-item>
          <el-upload
            ref="upload"
            :before-upload="onBeforeUpload"
            :on-success="uploadSuccess"
            :headers="headers"
            class="upload-demo"
            action="https://pic.xiaozhangstu.com/api/v1/upload"
            :file-list="temp.fileList"
            :auto-upload="true"
            multiple
            :limit="6"
            :on-exceed="handleExceed"
          >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip" style="width: 230px;">只能上传jpeg/jpg/png文件，且不超过 1Mb</div>
            <div slot="tip" class="el-upload__tip" style="width: 230px;">重新上传文件请刷新页面，移除图片无效</div>
          </el-upload>
        </el-form-item>
        <el-button type="primary" style="margin-left: 105px;" @click="createData()">
          提交
        </el-button>
      </el-form>

    </div>
  </div>
</template>

<script>
import { createArticle } from '@/api/charge/article'
// import waves from '@/directive/waves' // waves directive

export default {
  name: 'Charge',
  components: {},
  directives: {},
  filters: {},
  data() {
    return {
      temp: {
        chargeType: 'youkachongzhi',
        chargeAccount: '',
        chargeCardNumber1: '',
        chargeCardSecret1: '',
        chargeCardNumber2: '',
        chargeCardSecret2: '',
        chargeCardNumber3: '',
        chargeCardSecret3: '',
        chargeCardNumber4: '',
        chargeCardSecret4: '',
        chargeCardNumber5: '',
        chargeCardSecret5: '',
        chargeCardNumber6: '',
        chargeCardSecret6: '',
        fileList: []
      },
      rules: {
        chargeType: [{ required: true, message: '请填写充值账户', trigger: 'blur' }],
        chargeAccount: [{ required: true, message: '请选择充值类型', trigger: 'blur' }]
      },
      fileList: [],
      headers: {
        // 'Content-Type': 'application/json',
        'Authorization': 'Bearer 3|MHHlgGyBe2S63OvjffQf75yeIOLIdlVjl7yCWfOb',
        'Accept': 'application/json'
      }
    }
  },
  created() {
    this.clear()
  },
  methods: {
    clear() {
      this.fileList = []
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          console.log(this.temp)
          createArticle(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 6 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    onBeforeUpload(file) {
      const isIMAGE = file.type === 'image/jpeg' || 'image/jpg' || 'image/png'
      const isLt1M = file.size / 1024 / 1024 < 1

      if (!isIMAGE) {
        this.$message.error('上传文件只能是图片格式!')
      }
      if (!isLt1M) {
        this.$message.error('上传文件大小不能超过 1MB!')
      }
      return isIMAGE && isLt1M
    },
    uploadSuccess(response, file, fileList) {
      if (response.status) {
        var linksData = response.data.links
        var linkUrl = linksData.url
        var filename = response.data.name
        const fileJson = {
          'filepath': linkUrl,
          'filename': filename
        }
        var index = this.temp.fileList.length
        this.temp.fileList[index] = fileJson
        console.log(fileJson)
        console.log(this.temp)
      }
    }
  }
}
</script>

<style>
.charge {
  width: 100%;
  margin-left: -30px;

  /*margin: 0 auto 0;*/
}
.input_one{
  width: 200px;
  margin-left: 0px;
}
.text_one{

}
.youka{
  margin-right: 0px;
}
.qx{
  width: 1000px;
  height: 200px;
}
.qrtext{
  width: 160px;
}

.xqfile{

}
.pic-tiaofu {
  width: 50%;
  margin: 0 auto 0;
  //background-color: #0a76a4;
}

.pic-tiaofu img {
  width: 100%;
  height: 200px;
  margin-top: 1rem;
}
</style>
<style lang="scss" scoped>
$bg: #fff;
$dark_gray: #aaa;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
