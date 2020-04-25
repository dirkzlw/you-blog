package com.zlw.manager.web.controller;

import com.zlw.manager.po.Attention;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.web.utils.FastDFSUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dirk
 * @date 2020-04-18 16:23
 */
@Controller
public class AttentionController {

    @Autowired(required = false)
    private AttentionService attentionService;

    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;

    /**
     * 获取关注列表页面
     * @return
     */
    @GetMapping("/attention-list")
    public String toAttentionList(Model model){

        List<Attention> attentionList = attentionService.getAllAttention();
        model.addAttribute("attentionList", attentionList);

        return "attention/attention-list";
    }

    /**
     * 获取添加关注页面
     * @return
     */
    @GetMapping("/attention-add")
    public String toAttentionAdd(){
        return "attention/attention-add";
    }

    /**
     * 上传二维码
     * @param attentionImg
     * @return
     */
    @PostMapping("/attention/add")
    @ResponseBody
    public String addAttention(MultipartFile attentionImg){
        if(attentionImg == null){
            return "fail";
        }else {

            String imgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, attentionImg);
            if(null ==imgUrl || "".equals(imgUrl)){
                return "fail";
            }
            attentionService.saveAttention(new Attention(imgUrl));

            return "success";
        }
    }

    /**
     * 删除关注
     * @param attentionId
     * @return
     */
    @PostMapping("/attention/del")
    @ResponseBody
    public String delAttention(Integer attentionId){

        if(null == attentionId){
            return "fail";
        }else {
            return attentionService.delAttentionById(attentionId);
        }

    }

    /**
     * 获取修改关注页面
     * @param attentionId
     * @return
     */
    @GetMapping("/attention-edit")
    public String toAttentionEdit(Integer attentionId,Model model) {

        Attention attention = attentionService.findAttentionById(attentionId);
        model.addAttribute("attention", attention);

        return "attention/attention-edit";

    }

    /**
     * 修改关注
     * @param attentionId
     * @param attentionImg
     * @return
     */
    @PostMapping("/attention/edit")
    @ResponseBody
    public String editAttention(Integer attentionId,MultipartFile attentionImg){
        if(attentionImg == null || attentionId == null){
            return "fail";
        }else {

            String imgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, attentionImg);
            if(null ==imgUrl || "".equals(imgUrl)){
                return "fail";
            }
            Attention attention = new Attention(imgUrl);
            attention.setAttentionId(attentionId);

            attentionService.saveAttention(attention);

            return "success";
        }
    }
}
