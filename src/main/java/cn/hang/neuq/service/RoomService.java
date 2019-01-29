package cn.hang.neuq.service;

import cn.hang.neuq.base.BaseService;
import cn.hang.neuq.common.Response;
import cn.hang.neuq.common.ResponseMessageEnum;
import cn.hang.neuq.constant.InfoProperties;
import cn.hang.neuq.dao.DictDao;
import cn.hang.neuq.entity.dto.JwApiResDTO;
import cn.hang.neuq.entity.po.Dict;
import cn.hang.neuq.entity.po.Gpa;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.entity.vo.DictVO;
import cn.hang.neuq.entity.vo.QueryEmptyRoomConditionVO;
import cn.hang.neuq.entity.vo.QueryEmptyRoomVO;
import cn.hang.neuq.util.SessionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author lihang15
 * @description
 * @create 2019-01-26 01:03
 **/
@Service
@Slf4j
public class RoomService extends BaseService {
    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private DictDao dictDao;
    @Autowired
    private InfoProperties infoProperties;

    public Response<QueryEmptyRoomConditionVO> getQueryEmptyRoomInfo() {

        List<Dict> dictList = dictDao.listByType("empty_room_query_building");
        Map<String, List<DictVO>> dictMap = new HashMap<>();
        Map<String, DictVO> pDictMap = new HashMap<>();
        List<DictVO> buildingList = new ArrayList<>();
        for (int i = 0; i < dictList.size(); i++) {
            Dict dict = dictList.get(i);
            DictVO dictVO = new DictVO();
            BeanUtils.copyProperties(dict, dictVO);
            if (dict.getpId() == -1 && dictMap.get(dict.getId() + "") == null) {
                dictMap.put(String.valueOf(dict.getId()), new ArrayList<>());
                pDictMap.put(dict.getId() + "", dictVO);
            }
        }
        for (int i = 0; i < dictList.size(); i++) {
            Dict dict = dictList.get(i);
            if (dict.getpId() != -1 && dictMap.get(dict.getpId() + "") != null) {
                List<DictVO> list = dictMap.get(dict.getpId() + "");
                DictVO dictVO = new DictVO();
                BeanUtils.copyProperties(dict, dictVO);
                list.add(dictVO);
                dictMap.put(String.valueOf(dict.getpId()), list);
            }
        }
        for (Map.Entry<String, List<DictVO>> entry : dictMap.entrySet()) {
            DictVO dictVO = pDictMap.get(entry.getKey());
            dictVO.setChildDicts(entry.getValue());
            buildingList.add(dictVO);
        }
        QueryEmptyRoomConditionVO queryEmptyRoomConditionVO = new QueryEmptyRoomConditionVO();
        queryEmptyRoomConditionVO.setBuildingList(buildingList);
        queryEmptyRoomConditionVO.setCurrentWeekNumber(dictDao.getByType("current_week_number").getValue());
        queryEmptyRoomConditionVO.setTermWeekNumber(dictDao.getByType("term_week_number").getValue());
        return Response.success(queryEmptyRoomConditionVO);
    }

    public Response queryEmptyRoom(QueryEmptyRoomVO queryEmptyRoomVO) {
        UserJwInfo userJwInfo = sessionUtils.getJwUser();
        String res = null;
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            HttpEntity<String> formEntity = new HttpEntity<>(JSON.toJSONString(queryEmptyRoomVO), headers);
            String url = MessageFormat.format(infoProperties.getQueryEmptyRoomHost(), userJwInfo.getJwUsername(), userJwInfo.getJwPassword());
            res = client.postForObject(url, formEntity, String.class);
        } catch (RestClientException e) {
            log.error("network error", e);
            return Response.error(ResponseMessageEnum.NETWORK_ERROR);
        }
        JwApiResDTO<List<RoomInfo>> jwApiResDTO = JSON.parseObject(res, new TypeReference<JwApiResDTO<List<RoomInfo>>>() {
        });
        List<RoomInfo> roomInfoList = jwApiResDTO != null ? jwApiResDTO.getData() : Collections.emptyList();
        httpUtils.handleJwResponse(jwApiResDTO);
        log.info("jwxt queryEmptyRoom success userId={}", userJwInfo.getUserId());
        return Response.success(roomInfoList);
    }

    @Data
    private static class RoomInfo {
        private String cdjc;
        private String jxlmc;
        private String lh;
    }

}
