package com.neuq.info.service.impl;

import com.neuq.info.dao.CommentDao;
import com.neuq.info.dao.PostDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Comment;
import com.neuq.info.entity.Post;
import com.neuq.info.enums.ResultStatus;
import com.neuq.info.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

/**
 * Created by lihang on 2017/4/21.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private PostDao postDao;
    public ResultModel queryComment(long postid) {
        List<Comment> pList=commentDao.queryCommentByPostid(postid,1,0);

        System.out.println(pList.size());
        Post post =postDao.queryPostByPostId(postid);
        for (int i = 0; i <pList.size() ; i++) {
            List<Comment> cList=commentDao.queryCommentByPostid(postid,2,pList.get(i).getCommentId());
            pList.get(i).setcComments(cList);
            pList.get(i).setcCommentsSize(cList.size());
            pList.get(i).setFloor(i+1);
            if (pList.get(i).getFromUser().getUserId()==post.getUserId()){
                pList.get(i).setIsSelf(1);
            }


        }

        if(pList.size()==0){
            return new ResultModel(ResultStatus.NO_MORE_DATA);
        }
        return new ResultModel(ResultStatus.SUCCESS, pList);
    }

    public ResultModel addComment(String content, long fromUserId, long postid,int level,long pCommentId,long toUserId) {

        Comment comment =new Comment(postid,content);
        if(level==1){
            Post post =postDao.queryUserIdByPostId(postid);
            toUserId=post.getUserId();
        }
        int result=commentDao.insertComment(comment,fromUserId,toUserId,level,pCommentId);
        postDao.updateCommentCount(postid);
        if(result==0){
            return new ResultModel(ResultStatus.FAILURE);
        }else {
            return new ResultModel(ResultStatus.SUCCESS);
        }

    }
    public ResultModel delComment(long commentId,long userId) {
        long fromUserid= commentDao.queryCommentUserIdByCommentId(commentId);
        if (userId!=fromUserid){
            return new ResultModel(ResultStatus.NO_PERMISSION);
        }else {
            int result = commentDao.delComment(commentId);
            if (result == 0) {
                return new ResultModel(ResultStatus.FAILURE);
            } else {
                return new ResultModel(ResultStatus.SUCCESS);
            }
        }
    }


}
