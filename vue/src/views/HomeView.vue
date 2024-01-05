import request from "@/utils/request";
<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%;
               overflow-x: hidden"
      background-color="rgb(48, 65, 86)"
      text-color="#fff"
      active-text-color="#ffd04b"
      :collapse-transition="false"
      :collapse="isCollapse"
      >
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img src="../assets/基于云端传感器网络技术的智慧农业监测与控制系统LOGO.png" alt="" style="width: 50px; position: relative; top: 5px; margin-right: 5px">
        <b style="color: white" v-show="logoTextShow">设备管理系统</b>
      </div>
      <el-menu-item index="1" @click.native="$router.push('/')">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span slot="title">设备信息</span>
        </template>
<!--        <el-menu-item-group title="分组2">-->
<!--          <el-menu-item index="1-3">选项1</el-menu-item>-->
<!--        </el-menu-item-group>-->
<!--        <el-submenu index="1-4">-->
<!--          <template slot="title">选项2</template>-->
<!--          <el-menu-item index="1-4-1">选项2-1</el-menu-item>-->
<!--        </el-submenu>-->
      </el-menu-item>
      <el-menu-item index="2" @click.native="$router.push('/feedback')">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span slot="title">反馈</span>
        </template>
<!--        <el-submenu index="2-4">-->
<!--          <template slot="title">选项3</template>-->
<!--          <el-menu-item index="2-4-1">3-1</el-menu-item>-->
<!--        </el-submenu>-->
<!--      </el-submenu>-->
<!--      <el-submenu index="3">-->
<!--        <template slot="title">-->
<!--          <i class="el-icon-setting"></i>-->
<!--          <span slot="title">导航三</span>-->
<!--        </template>-->
<!--        <el-submenu index="3-4">-->
<!--          <template slot="title">选项4</template>-->
<!--          <el-menu-item index="3-4-1">选项4-1</el-menu-item>-->
<!--        </el-submenu>-->
      </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>

<!--      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">-->
      <el-header style="display: flex; justify-content: space-between; align-items: center; font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px;">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <div class="header-title">基于云端传感器网络技术的智慧农业监测与控制系统</div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>用户</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main>
        <div style="margin-bottom: 30px">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">设备管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        </div>

        <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="请输入设备名称" suffix-icon="el-icon-search" v-model="name"></el-input>
          <el-input style="width: 200px" placeholder="请输入唯一标识" suffix-icon="el-icon-message" class="ml-5" v-model="equid"> </el-input>
          <el-input style="width: 200px" placeholder="请输入位置" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定批量删除这些数据吗？"
              @confirm="delBatch"
          >
          <el-button type="danger" slot="reference" >批量删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
<!--          <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>-->
<!--          <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>-->
        </div>
        <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="Id" width="80"></el-table-column>
          <el-table-column prop="name" label="设备名称" width="140"></el-table-column>
          <el-table-column prop="equstatu" label="运行状态" width="120"></el-table-column>
          <el-table-column prop="equid" label="唯一标识" width="90"></el-table-column>
          <el-table-column prop="need" label="需求信息" ></el-table-column>
          <el-table-column prop="address" label="位置" width="100"></el-table-column>
          <el-table-column label="操作"  width="280" align="center">

            <template slot-scope="scope">
<!--              <el-button type="success" @click="handleEdit(scope.row)">反馈 <i class="el-icon-edit"></i></el-button>-->

<!--              <el-dialog title="反馈" :visible.sync="dialogEditFormVisible">-->
<!--                <el-form>-->

<!--&lt;!&ndash;                  <el-form-item label="设备名称">&ndash;&gt;-->
<!--&lt;!&ndash;                    <el-input v-model="editForm.name"></el-input>&ndash;&gt;-->
<!--&lt;!&ndash;                  </el-form-item>&ndash;&gt;-->

<!--&lt;!&ndash;                  <el-form-item label="反馈信息">&ndash;&gt;-->
<!--&lt;!&ndash;                    <el-input&ndash;&gt;-->
<!--&lt;!&ndash;                        type="textarea"&ndash;&gt;-->
<!--&lt;!&ndash;                        autosize&ndash;&gt;-->
<!--&lt;!&ndash;                        v-model="editForm.feedback"&ndash;&gt;-->
<!--&lt;!&ndash;                    ></el-input>&ndash;&gt;-->
<!--&lt;!&ndash;                  </el-form-item>&ndash;&gt;-->

<!--                </el-form>-->
<!--                <template slot="footer">-->
<!--&lt;!&ndash;                  <el-button @click="dialogEditFormVisible = false">取 消</el-button>&ndash;&gt;-->
<!--                  <el-button type="primary" @click="dialogEditFormVisible = false">确 定</el-button>-->
<!--                </template>-->
<!--              </el-dialog>-->

<!--              <el-popconfirm-->
<!--                  class="ml-5"-->
<!--                  confirm-button-text='确定'-->
<!--                  cancel-button-text='取消'-->
<!--                  icon="el-icon-info"-->
<!--                  icon-color="red"-->
<!--                  title="确定删除吗？"-->
<!--                  @confirm="del(scope.row.id)"-->
<!--              >-->
                <el-button type="primary" style="margin-left: 5px;" @click="start(scope.row)">启动</el-button>

                <el-button type="danger" style="margin-left: 5px;" @click="stop(scope.row)">终止</el-button>
<!--              </el-popconfirm>-->

            </template>
          </el-table-column>
        </el-table>


        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>

        <el-dialog title="新增" :visible.sync="dialogFormVisible" width="30%">
          <el-form label-width="80px" size="large">
            <el-form-item label="设备名称" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
<!--            <el-form-item label="设备状态" :label-width="formLabelWidth">-->
<!--              <el-input v-model="form.equstatu" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
            <el-form-item label="唯一标识" :label-width="formLabelWidth">
              <el-input v-model="form.equid" autocomplete="off"></el-input>
            </el-form-item>
<!--            <el-form-item label="反馈信息" :label-width="formLabelWidth">-->
<!--              <el-input v-model="form.feedback" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
            <el-form-item label="位置" :label-width="formLabelWidth">
              <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>



      </el-main>

    </el-container>
  </el-container>
</template>

<script>

export default {
  name: 'Home',
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 20,
      name: "",
      equid: "",
      address: "",
      form: {},
      editForm: {},
      // editedFeedback: "",
      dialogFormVisible: false,
      dialogEditFormVisible: false,
      multipleSelection: [],
      msg: "hello",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,

    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          equid: this.equid,
          equstatu: this.equstatu,
          need: this.need,
          address: this.address
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    save() {
      this.editForm.need = this.editedNeed;
      this.request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    // handleEdit(row) {
    //   this.editForm = {
    //     name: row.name,
    //     feedback: row.feedback
    //   };
    //   this.editedFeedback = row.feedback;
    //   this.dialogEditFormVisible = true;
    // },

    // del(id) {
    //   this.request.delete("/user/" + id).then(res => {
    //     if (res) {
    //       this.$message.success("删除成功")
    //       this.load()
    //     } else {
    //       this.$message.error("删除失败")
    //     }
    //   })
    // },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    reset() {
      this.name = ""
      this.equid = ""
      this.address = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    start(row) {
      this.$message.success('启动成功')
      row.equstatu = '运行中'
    },
    stop(row) {
      this.$message.success('终止成功')
      row.equstatu = '停止'
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
.header-title {
  flex: 1;
  text-align: center;
  font-size: 20px;
  margin-left: -1200px;
}
</style>
