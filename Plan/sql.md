## Tables

```sql
create table Student(
Sid varchar(32),
Sname varchar(32),
Sage smallint,
Ssex char(1)
);

create table Course(
Cid varchar(32),
Cname varchar(32),
Tid varchar(32)
);

create table SC(
Sid varchar(32),
Cid varchar(32),
score smallint
);

create table Teacher(
Tid varchar(32),
Tname varchar(32)
);
```

## Data

```sql
INSERT INTO `Student`(`Sid`, `Sname`, `Sage`, `Ssex`) VALUES ('1','XiaoMing',25,1);
INSERT INTO `Student`(`Sid`, `Sname`, `Sage`, `Ssex`) VALUES ('2','Baby',24,0);
INSERT INTO `Student`(`Sid`, `Sname`, `Sage`, `Ssex`) VALUES ('3','XiaoMing2',25,1);
INSERT INTO `Student`(`Sid`, `Sname`, `Sage`, `Ssex`) VALUES ('4','Baby2',24,0);
INSERT INTO `Student`(`Sid`, `Sname`, `Sage`, `Ssex`) VALUES ('5','XiaoMing3',25,1);
INSERT INTO `Student`(`Sid`, `Sname`, `Sage`, `Ssex`) VALUES ('6','Baby3',24,0);

INSERT INTO `Teacher`(`Tid`, `Tname`) VALUES ('1','Txiamin');
INSERT INTO `Teacher`(`Tid`, `Tname`) VALUES ('2','Txhu');

INSERT INTO `Course`(`Cid`, `Cname`, `Tid`) VALUES ('1','文学史','1');
INSERT INTO `Course`(`Cid`, `Cname`, `Tid`) VALUES ('3','考古学','1');
INSERT INTO `Course`(`Cid`, `Cname`, `Tid`) VALUES ('2','心理学','2');

insert into `SC` (`Sid`,`Cid`,`score`) values ('1','1',100);
insert into `SC` (`Sid`,`Cid`,`score`) values ('2','1',80);
insert into `SC` (`Sid`,`Cid`,`score`) values ('3','1',52);
insert into `SC` (`Sid`,`Cid`,`score`) values ('4','1',18);

insert into `SC` (`Sid`,`Cid`,`score`) values ('1','2',90);
insert into `SC` (`Sid`,`Cid`,`score`) values ('2','2',100);

insert into `SC` (`Sid`,`Cid`,`score`) values ('3','3',95);
insert into `SC` (`Sid`,`Cid`,`score`) values ('4','3',88);
insert into `SC` (`Sid`,`Cid`,`score`) values ('5','3',85);
insert into `SC` (`Sid`,`Cid`,`score`) values ('6','3',58);
```

## Prob

### **查询“某1”课程比“某2”课程成绩高的所有学生的学号；**

```sql
select a.Sid,a.score from(select * from SC 
where Cid='1') a, (SELECT * from SC 
WHERE Cid='2') b 
WHERE a.score>b.score and a.Sid=b.Sid;

select a.Sid,a.Sname,a.score,a.Cname from(
select stu.*, sc.score,cos.Cname from SC sc,Course cos,Student stu 
where sc.Cid='1' and sc.Cid=cos.Cid and stu.Sid=sc.Sid) a, (
SELECT * from SC 
WHERE SC.Cid='2') b 
WHERE a.score>b.score and a.Sid=b.Sid;
```

	- 按照课程成绩排名

```sql
SELECT stu.Sid,stu.Sname,sc.score,cos.Cname,tea.Tname 
from Student stu,SC sc,Course cos,Teacher tea 
where stu.Sid=sc.Sid and sc.Cid=cos.Cid and cos.Tid=tea.Tid 
order by cos.Cid, sc.score desc;
```

### **查询平均成绩大于60分的同学的学号和平均成绩；**

```sql
select Sid,avg(score) as ag from SC 
group by Sid 
having ag>50;

select stu.Sid,stu.Sname,res.ag from (
select Sid,avg(score) as ag from SC 
group by Sid 
having ag>50) res, Student stu 
where stu.Sid=res.Sid
```

### **查询所有同学的学号、姓名、选课数、总成绩**

	- 只能查询出选了课程的学生的记录

```sql
select stu.Sname,res.* from (
select distinct Sid,count(1),sum(score) from SC sc 
group by Sid) res, Student stu 
where stu.Sid=res.Sid;
```

	- 可以查询所有学生的记录

```sql
SELECT Student.Sid,Student.Sname,count(SC.Cid),sum(score)
FROM Student left Outer JOIN SC on Student.Sid=SC.Sid 
GROUP BY Student.Sid;

SELECT Student.Sid,Student.Sname,COUNT(1),SUM(SC.score) 
FROM Student LEFT OUTER JOIN SC ON SC.Sid=Student.Sid 
GROUP BY Student.Sid;
```

### **查询姓“李”的老师的个数；**

```sql
select count(1) from Teacher 
where Tname like 'Tx%';
```

### **查询没学过“叶平”老师课的同学的学号、姓名；**

 
	- 只能查询得到有选课的学生记录

```sql
select sc.Sid from SC sc 
where sc.Sid not exists(select Sid from Course,SC, Teacher 
where Teacher.Tid='2' and Teacher.Tid=Course.Tid and Course.Cid=SC.Cid);

Select stu.Sid,stu.Sname from (select sc.Sid from SC sc 
where sc.Sid not in(select Sid from Course,SC, Teacher 
where Teacher.Tid='2' and Teacher.Tid=Course.Tid and Course.Cid=SC.Cid)) res, Student stu 
where res.Sid=stu.Sid
```

	- 可以查询没选课的学生的记录

```sql
Select stu.Sid,stu.Sname from Student stu 
WHERE stu.Sid not in(select Sid from Course,SC, Teacher 
where Teacher.Tid='2' and Teacher.Tid=Course.Tid and Course.Cid=SC.Cid)
```

### **查询学过“```”并且也学过编号“```”课程的同学的学号、姓名；**

```sql
select sc.Sid,count(1) from SC sc 
where (sc.Cid='1' or sc.Cid='2') 
group by sc.Sid;

SELECT stu.Sid,stu.Sname from Student stu 
where stu.Sid in(select Sid from (select sc.Sid Sid,count(1) ct from SC sc 
where sc.Cid='1' or sc.Cid='2' 
group by sc.Sid) res 
where res.ct>=2)
```

### **查询学过“Txiamin”老师所教的所有课的同学的学号、姓名；**

	- Txiamin老师教过的课程id

```sql
select distinct sc.Cid from SC sc,Teacher tea,Course cos 
where tea.Tid='1' and tea.Tid=cos.Tid and cos.Cid=sc.Cid;

Select stu.Sid,stu.Sname from Student stu,SC sc 
where sc.Cid in(select distinct sc.Cid Cid from SC sc,Teacher tea,Course cos 
where tea.Tid='1' and tea.Tid=cos.Tid and cos.Cid=sc.Cid) and sc.Sid=stu.Sid;
```

### **查询课程编号“”的成绩比课程编号“”课程低的所有同学的学号、姓名；**

	- 查询同学的课程成绩

```sql
select a.Sid,a.score from (select Sid, score from SC 
where SC.Cid='2') a,(select Sid,score from SC 
where SC.Cid='1') b 
where a.Sid=b.Sid and a.score<b.score;
```

### **查询所有课程成绩小于分的同学的学号、姓名；**

```sql
select stu.Sid,stu.Sname from Student stu 
where stu.Sid in (select Sid from SC 
where score<92);

select stu.Sid, stu.Sname, sc.score from Student stu, SC sc 
where stu.Sid=sc.Sid and sc.score<92;
```
### **查询没有学全所有课的同学的学号、姓名；**

```sql
select stu.Sid,count(1) ct from Student stu right Outer JOIN SC sc on sc.Sid=stu.Sid 
group by stu.Sid 
having ct>=(select count(1) from Course);
```
**查询所有同学的选课数目**

	- 不能处理没有选课的学生记录

```
select sc.Sid,count(1) from SC sc 
group by sc.Sid;
```

	- 可以处理没有选课的学生记录

```
select stu.Sid,count(1) from Student stu left JOIN SC sc on sc.Sid=stu.Sid 
group by stu.Sid;
```

**查询课程数目**

```
select count(1) from Course;
```

**答案**

	- 不能处理没有选课的学生记录

```
select stu.Sid,count(1) ct from Student stu,SC sc 
where sc.Sid=stu.Sid 
group by stu.Sid  
having ct<(select count(1) from Course);
```

	- 可以处理没有选课的学生记录

```
select stu.Sid,count(1) ct from Student stu left JOIN SC sc on sc.Sid=stu.Sid 
group by stu.Sid 
having ct<(select count(1) from Course);
```

### **查询至少有一门课与学号为“1”的同学所学相同的同学的学号和姓名；**

```
select stu.Sid,stu.Sname from Student stu,SC sc 
where stu.Sid=sc.Sid and stu.Sid<>'1' and sc.Cid in( select Cid from SC 
where Sid='1');
```

### **查询至少学过学号为“1”同学所有课的其他同学学号和姓名**

```
select Sid,Sname from Student 
where Sid in(select Sid from SC 
where Cid in(select Cid from SC 
where Sid='1') 
group by Sid 
having count(1)>=(select count(Cid) from SC 
where Sid='1'))
```

### **查询每门课程被选修的学生数**

```
select Cid,count(1),Sid from SC 
group by Cid,Sid
select Cid,count(1),Sid from SC 
group by Sid,Cid
```

### **查询出只选修了一门课程的全部学生的学号和姓名**

```
select Sid,Sname from Student 
where Sid in(select Sid from SC 
group by Sid 
having count(1)=1)
```

### **查询不及格的课程，并按课程号从大到小排列**

```
select Cname,sc.score from Course cos,SC sc 
where cos.Cid=sc.Cid and sc.score<60 
order by sc.Cid DESC
```

### **查询每门课程的平均成绩，结果按平均成绩升序排列，平均成绩相同时，按课程号降序排列**

```
select avg(score) avgs,sc.Cid,cos.Cname from Course cos,SC sc 
where cos.Cid=sc.Cid 
group by sc.Cid 
order by avgs ASC,sc.Cid DESC
```

### **查询课程编号为X且课程成绩在X分以上的学生的学号和姓名**

```
select stu.Sid,stu.Sname,sc.score from Student stu,SC sc where stu.Sid=sc.Sid and sc.Cid='1' and sc.score>70;
```

### **查询选修“”老师所授课程的学生中，成绩最高的学生姓名及其成绩**

```
select stu.Sid,Sname,max(score),cos.Cname from Student stu,SC sc,Teacher tea,Course cos 
where tea.Tname='Tximin' and tea.Tid=cos.Tid and cos.Cid=sc.Cid and sc.Sid=stu.Sid 
group by sc.Cid;
```

**各科最高分**

```
select stu.Sid,Sname,max(score),cos.Cname from Student stu,SC sc,Teacher tea,Course cos 
where tea.Tid=cos.Tid and cos.Cid=sc.Cid and sc.Sid=stu.Sid 
group by sc.Cid;
```

### **统计每门课程的学生选修人数**

**要求输出课程号和选修人数，查询结果按人数降序排列；若人数相同，按课程号升序排列**

```
select Cid,count(1) ct from SC 
group by Cid 
order by ct DESC,Cid ASC
```

###	**检索至少选修两门课程的学生学号**

```
select Sid,count(1) ct from SC 
group by Sid 
having ct>=2
```

### **查询没学过“XX”老师讲授的任一门课程的学生姓名**

```
select Sid,Sname from Student 
where Sid not in(select sc.Sid from SC sc,Course cos,Teacher tea 
where tea.Tname='Tximin' and tea.Tid=cos.Cid and cos.Cid=sc.Cid)
```

### **查询两门以上不及格课程的同学的学号及其平均成绩**

```
select avg(score),Sid from SC 
where score<92 
group by Sid 
having count(1)>=2
```

### **检索“XX”课程分数小于X，按分数降序排列的同学学号**

```
select Sid,score from SC 
where score<70 and Cid='1' 
order by Sid DESC
```