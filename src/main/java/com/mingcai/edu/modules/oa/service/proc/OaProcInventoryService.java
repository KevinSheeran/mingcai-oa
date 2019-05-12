/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.proc;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.dao.proc.OaProcInventoryDao;
import com.mingcai.edu.modules.oa.entity.proc.OaProcInventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购清单Service
 * @author 谢一郡
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class OaProcInventoryService extends CrudService<OaProcInventoryDao, OaProcInventory> {

	public OaProcInventory get(String id) {
		return super.get(id);
	}
	
	public List<OaProcInventory> findList(OaProcInventory oaProcInventory) {
		return super.findList(oaProcInventory);
	}
	
	public Page<OaProcInventory> findPage(Page<OaProcInventory> page, OaProcInventory oaProcInventory) {
		return super.findPage(page, oaProcInventory);
	}


	@Transactional(readOnly = false)
	public void save(OaProcInventory oaProcInventory) {
		super.save(oaProcInventory);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProcInventory oaProcInventory) {
		super.delete(oaProcInventory);
	}
	
}