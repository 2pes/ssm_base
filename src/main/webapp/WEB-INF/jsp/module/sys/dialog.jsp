<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="userform" action="${ctx }/book/1003/appoint" method="post">
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
		bgColor=#c4d8ed>

		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;系统用户信息</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>

			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>

							<TR>
								<TD height=30 width="15%" align=right>用户账号：</TD>
								<TD class=category width="35%">
									<div>
										<input type="text" id="sysuser_userid"
											name="sysuserCustom.userid" />
									</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
									<div id="sysuser_useridTip"></div>
								</TD>
								<TD height=30 width="15%" align=right>用户名称：</TD>
								<TD class=category width="35%">
									<div>
										<input type="text" id="sysuser_username"
											name="sysuserCustom.username" />
									</div>
									<div id="sysuser_usernameTip"></div>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right>用户密码：</TD>
								<TD class=category width="35%">
									<div>
										<input type="password" id="sysuser_password"
											name="sysuserCustom.pwd" />
									</div>
									<div id="sysuser_passwordTip"></div>
								</TD>
								<TD height=30 width="15%" align=right>用户类型：</TD>
								<TD class=category width="35%">
									<div>
										<select name="sysuserCustom.groupid" id="sysuser_groupid">

											<option value="">请选择</option>
											<option value="1">卫生局</option>
											<option value="2">卫生院</option>
											<option value="3">卫生室</option>
											<option value="4">供货商</option>
											<option value="0">系统管理员</option>

										</select>
									</div>
									<div id="sysuser_groupidTip"></div>
								</TD>


							</TR>
							<TR>
								<TD height=30 width="15%" align=right>用户单位名称：</TD>
								<!-- 用处：根据名称获取单位id -->
								<TD class=category width="35%"><input type="text"
									name="sysuserCustom.sysmc" /></TD>
								<TD height=30 width="15%" align=right>用户状态：</TD>
								<TD class=category width="35%"><input type="radio"
									name="sysuserCustom.userstate" value="1" />正常 <input
									type="radio" name="sysuserCustom.userstate" value="0" />暂停</TD>

							</TR>
							<tr>
								<td colspan=4 align=center class=category><a id="submitbtn"
									class="easyui-linkbutton" iconCls="icon-ok" href="#"
									onclick="bookAppoint(1003);">提交</a> <a id="closebtn"
									class="easyui-linkbutton" iconCls="icon-cancel" href="#"
									onclick="parent.closemodalwindow()">关闭</a></td>
							</tr>

						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>

<script type="text/javascript">
	
</script>