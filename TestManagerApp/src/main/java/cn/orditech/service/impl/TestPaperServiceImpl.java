
package cn.orditech.service.impl;

import cn.orditech.dao.TestPaperDao;
import cn.orditech.dao.impl.TestPaperDaoImpl;
import cn.orditech.entity.Question;
import cn.orditech.enums.QuestionTypeEnum;
import cn.orditech.service.QuestionService;
import cn.orditech.service.TestPaperService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.TestPaper;
import cn.orditech.tool.ReadWordDocUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class TestPaperServiceImpl extends BaseService<TestPaper> implements TestPaperService{
    @Autowired
    private TestPaperDao testPaperDao;

    @Autowired
    private QuestionService questionService;

    protected BaseDao<TestPaper> getDao(){
        return (TestPaperDaoImpl)this.testPaperDao;
    }

    @Override
    public List<TestPaper> findByIds (List<Long> ids) {
        return testPaperDao.findByIds (ids);
    }

    @Override
    public void analysisTestPaperAndSave(List<String>rows){
        TestPaper testPaper = new TestPaper();
        //��һ�����Ծ�ı���
        String title = rows.get(0);
        testPaper.setTitle(title);

        int singleNum =0;//��ѡ������
        int singleScore=0;//��ѡ��ÿ��ķ���
        int multipleNum=0;//��ѡ������
        int multipleScore=0;//��ѡ��ÿ��ķ���
        int judgeNum=0;//�ж�������
        int judgeScore=0;//�ж���ÿ��ķ���
        /***********�Ծ�������ݸ�ֵ*********************/
        for (int i = 0; i < rows.size(); i++) {
            String delHtml = rows.get(i);
            if(delHtml.contains("����ѡ��")){
                String numScore= ReadWordDocUtils.numScore(delHtml);
                singleNum= Integer.parseInt(numScore.split(",")[0]) ;
                singleScore=Integer.parseInt(numScore.split(",")[1]) ;
            }else if(delHtml.contains("����ѡ��")){
                String numScore=ReadWordDocUtils.numScore(delHtml);
                multipleNum= Integer.parseInt(numScore.split(",")[0]) ;
                multipleScore=Integer.parseInt(numScore.split(",")[1]) ;
            }else if(delHtml.contains("���ж���")){
                String numScore=ReadWordDocUtils.numScore(delHtml);
                judgeNum= Integer.parseInt(numScore.split(",")[0]) ;
                judgeScore=Integer.parseInt(numScore.split(",")[1]) ;
            }
        }

        JSONArray questionJsonArray = new JSONArray();
        JSONObject jsonObject;
        int counter = 0;
        int i=1;
        while(i < rows.size()){
            if(rows.get(i).contains("���ж���") || counter < judgeNum){//��ʼ�����ж��⣬��Ϊ�涨word�ĵ���һ���ж���ռ4��
                while (rows.get(i)==""){
                    i++;
                }
                if(ReadWordDocUtils.isBigTilete(rows.get(i))){
                    i++;
                }

                String smallTitle = rows.get(i++);

                String option1 = rows.get(i++);
                String option2 = rows.get(i++);
                String rightAnser = rows.get(i++);

                //��������
                Question question = new Question();
                question.setGmtCreate(new Date());
                question.setGmtModified(new Date());
                question.setType(QuestionTypeEnum.JUDGE.getType());
                question.setTitle(smallTitle.split("��")[1]);
                question.setScore(judgeScore);
                question.setAnswer(rightAnser.split(":|��")[1].equals("��ȷ")?"R":"W");

                JSONArray judgeOptionArray = new JSONArray();
                jsonObject = new JSONObject();
                jsonObject.put("mark","R");
                jsonObject.put("value","��ȷ");
                judgeOptionArray.add(jsonObject);
                jsonObject = new JSONObject();
                jsonObject.put("mark","W");
                jsonObject.put("value","����");
                judgeOptionArray.add(jsonObject);
                question.setOptions(judgeOptionArray.toJSONString());//�ж����ѡ���ǹ̶��ģ����д����

                questionService.insert(question);

                jsonObject = new JSONObject();
                jsonObject.put("id",question.getId());
                jsonObject.put("score",judgeScore);
                questionJsonArray.add(jsonObject);

                counter++;
            }else if (rows.get(i).contains("����ѡ��") || (counter-judgeNum) < singleNum){
                while (rows.get(i)==""){
                    i++;
                }
                if(ReadWordDocUtils.isBigTilete(rows.get(i))){
                    i++;
                }

                String smallTitle = rows.get(i++);
                smallTitle = smallTitle.split("��")[1];
                String optionA = rows.get(i++);
                optionA = optionA.split("\\.")[1];
                String optionB = rows.get(i++);
                optionB = optionB.split("\\.")[1];
                String optionC =  rows.get(i++);
                optionC = optionC.split("\\.")[1];
                String optionD = rows.get(i++);
                optionD = optionD.split("\\.")[1];
                String rightAnswer =  rows.get(i++);
                rightAnswer = rightAnswer.split(":|��")[1];

                //���쵥ѡ��ѡ���Ӧ�� JSON��
                JSONArray singleSelectOptionArray = new JSONArray();
                char c = 'A';
                for(int j=0;j < 4;j++){
                    String option = "";
                    switch (j) {
                        case 0:
                            c = 'A';
                            option = optionA;
                            break;
                        case 1:
                            c='B';
                            option = optionB;
                            break;
                        case 2:
                            c='C';
                            option = optionC;
                            break;
                        case 3:
                            c='D';
                            option = optionD;
                    }

                    jsonObject = new JSONObject();
                    jsonObject.put("mark",""+c);
                    jsonObject.put("value",option);
                    singleSelectOptionArray.add(jsonObject);
                }

                Question question = new Question();
                question.setTitle(smallTitle);
                question.setScore(singleScore);
                question.setType(QuestionTypeEnum.SINGLE_SELECT.getType());
                question.setOptions(singleSelectOptionArray.toJSONString());
                question.setAnswer(rightAnswer);
                question.setGmtCreate(new Date());
                question.setGmtModified(new Date());
                questionService.insert(question);

                jsonObject = new JSONObject();
                jsonObject.put("id",""+question.getId());
                jsonObject.put("score",""+singleScore);

                questionJsonArray.add(jsonObject);
                counter++;
            }else if(rows.get(i).contains("����ѡ��") || (counter-judgeNum-singleNum) < multipleNum){
                while (rows.get(i)==""){
                    i++;
                }
                if(ReadWordDocUtils.isBigTilete(rows.get(i))){
                    i++;
                }

                String smallTitle = rows.get(i++);
                smallTitle = smallTitle.split("��")[1];
                String optionA = rows.get(i++);
                optionA = optionA.split("\\.")[1];
                String optionB = rows.get(i++);
                optionB = optionB.split("\\.")[1];
                String optionC =  rows.get(i++);
                optionC = optionC.split("\\.")[1];
                String optionD = rows.get(i++);
                optionD = optionD.split("\\.")[1];
                String rightAnswer =  rows.get(i++);
                rightAnswer = rightAnswer.split(":|��")[1];

                //�����ѡ��ѡ���Ӧ�� JSON��
                JSONArray multiSelectOptionJsonArray = new JSONArray();
                char c = 'A';
                for(int j=0;j < 4;j++){
                    String option = "";
                    switch (j) {
                        case 0:
                            c = 'A';
                            option = optionA;
                            break;
                        case 1:
                            c='B';
                            option = optionB;
                            break;
                        case 2:
                            c='C';
                            option = optionC;
                            break;
                        case 3:
                            c='D';
                            option = optionD;
                    }

                    jsonObject = new JSONObject();
                    jsonObject.put("mark",""+c);
                    jsonObject.put("value",option);

                    multiSelectOptionJsonArray.add(jsonObject);

                }

                Question question = new Question();
                question.setTitle(smallTitle);
                question.setScore(singleScore);
                question.setType(QuestionTypeEnum.MULTI_SELECT.getType());
                question.setOptions(multiSelectOptionJsonArray.toJSONString());
                char[] array = rightAnswer.toCharArray();
                rightAnswer = "";
                for(int j=0;j < array.length;j++){
                    rightAnswer+=array[j];
                    rightAnswer+=",";
                }
                rightAnswer = rightAnswer.substring(0,rightAnswer.lastIndexOf(','));
                question.setAnswer(rightAnswer);
                question.setGmtCreate(new Date());
                question.setGmtModified(new Date());
                questionService.insert(question);

                jsonObject = new JSONObject();
                jsonObject.put("id",""+question.getId());
                jsonObject.put("score",""+multipleScore);

                questionJsonArray.add(jsonObject);
                counter++;
            }else {
                i++;
            }
        }

        testPaper.setQuestions(questionJsonArray.toJSONString());
        testPaper.setScore(judgeNum*judgeScore+singleNum*singleScore+multipleNum*multipleScore);
        testPaper.setGmtCreate(new Date());
        testPaper.setGmtModified(new Date());
        this.insert(testPaper);

    }
}