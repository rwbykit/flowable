/*
package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import java.util.List;


*/
/**
 * 范围层级接口
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午4:59:13
 * @version 1.0
 *//*

public interface RelationshipHierarchyService {
    
    */
/**
     * 当前
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getCurrentOrg(InitiatingStaff user);
    
    */
/**
     * 上级
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getSuperOrg(InitiatingStaff user);
    
    */
/**
     * 上级的下级
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getSuperSubOrg(InitiatingStaff user);
    
    */
/**
     * 本级及上级
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getCurrAndSuperOrg(InitiatingStaff user);
    
    */
/**
     * 上上级
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getDoubleSuperOrg(InitiatingStaff user);
    
    */
/**
     * 上级和上上级
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getSuperAndDoubleSuperOrg(InitiatingStaff user);
    
    */
/**
     * 上上级的下级
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getDoubleSuperSubOrg(InitiatingStaff user);
    
    */
/**
     * 下级机构办理者
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getSubOrg(InitiatingStaff user);
    
    */
/**
     * 流程发起者
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getProcessStartUser(InitiatingStaff user);
    
    */
/**
     * 已审批
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getApprovedUser(String processInstId);

    */
/**
     * 机构领导
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getOrgLeader(InitiatingStaff user);
    
    */
/**
     * 当前部门
     * @param id
     * @return
     *//*

    List<AssigneeInformation> getCurrDept(InitiatingStaff user);
    
}
*/
