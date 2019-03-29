const debug = process.env.NODE_ENV === "development" ? true : false;
// 测试环境地址
if (debug) {
  var host = "http://10.14.10.181:8880";
} else {
  //正式环境地址
  var host = "http://10.14.10.181:8880";
}

let resources = {
  schoolId: sessionStorage.getItem("schoolId") || 0,
  host: host,
  getEmp() {
    return `http://localhost:8080/ssm-crud/emp/select`;
  },
  getEmps() {
    return `http://localhost:8080/ssm-crud/emps/select`;
  },
  addEmp(){
    return `http://localhost:8080/ssm-crud/emps/add`;
  },
  getDepts(){
    return `http://localhost:8080/ssm-crud/depts/select`;
  },
  updateEmp(){
    return `http://localhost:8080/ssm-crud/emp/update`;
  },
  deleteEmp(){
    return `http://localhost:8080/ssm-crud/emp/delete`;
  },
  deleteEmps(){
    return `http://localhost:8080/ssm-crud/emps/delete`;
  },
  checkUser(){
    return `http://localhost:8080/ssm-crud/emp/checkuser`;
  },
  
};
export default resources;
