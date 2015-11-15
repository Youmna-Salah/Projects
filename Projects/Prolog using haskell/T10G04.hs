------------------------------------------------------------PART A----------------------------------------------------------
data Term =Variable String 
         | Constant String 
           deriving(Show,Eq)          
data Prdct =Predicate String [Term]
           deriving (Eq,Show) 
type Goals =[Prdct]
data Rle =Rule (Prdct,Goals)
            deriving (Eq,Show) 
data Solution =Yes [Term] 
              |No
              deriving (Eq,Show) 
--------------------------------------------------------------PART B------------------------------------------------------------------               
inunify [] [] =[]  
inunify (x:xs)(y:ys)= 
	 if check(x:xs)(y:ys)then(innunify (x:xs) (y:ys))++(inunify xs ys)else []
innunify ((Variable x):xs)((Constant y):ys)=[Variable x,Constant y]
innunify((Constant a):ys)((Constant b):xs)=[]
innunify (Variable x:xs)(Variable y:ys) =[Variable y,Variable x]
innunify ((Constant y):ys) ((Variable x):xs)=[Variable x,Constant y]



check [] [] =True
check (x:xs) []=False
check [] (x:xs)=False
check [Constant a] [Variable c]=True
check((Constant a):as) ((Constant b):bs)| a==b =True && check as bs
                                        |otherwise =False
check((Variable a):as) ((Constant b):bs)=True &&check as bs
check((Variable a):as) ((Variable b):bs)=True &&check as bs
check ((Constant a):as) ((Variable b):bs)=True &&check as bs
callcheck a l []=True
callcheck a l ((Predicate cc (e:es)):fs)=if a==cc then(check l (e:es))|| callcheck a l fs else callcheck a l fs 

unify(Predicate a (b:bs))  []=[]
unify(Predicate a (b:bs))((Predicate cc (e:es)):fs)=
    if (cc==a && length (inunify(b:bs)(e:es))>0)
    	then inunify(b:bs)(e:es)++unify(Predicate a (b:bs)) fs
    	 else unify(Predicate a (b:bs)) fs

unifyWithHead(Predicate a (b:bs))((Predicate cc (e:es)):fs)=
    if length(unify(Predicate a (b:bs)) ((Predicate cc (e:es)):fs))==0 &&not(callcheck a (b:bs) ((Predicate cc (e:es)):fs))
    	then No
    	  else Yes (unify(Predicate a (b:bs))((Predicate cc (e:es)):fs))
------------------------------------------------------------PART C-----------------------------------------------------------------------
search [] l =[]
search(x:xs) l=if contains x l then x:search xs l else search xs l

contains x [] =False
contains x (l:ls) =if(x==l) then True else contains x ls 

getSimlarties []= []
getSimlarties(x:(y:ys)) =(merge x y)++getSimlarties ys

searchin [] []=[]
searchin x [] =[]
searchin [] x =[]
searchin (v:vs)(a:b:as)= if v==a then b:searchin (v:vs) as else searchin vs (a:b:as)    
unifyPredicate [] l=[]
unifyPredicate (x:y:xs) (Predicate n (s:sx)) =if (repl (x:y:xs) (Predicate n (s:sx)))==(Predicate n (s:sx)) then unifyPredicate xs (Predicate n (s:sx))  else [repl(x:y:xs) (Predicate n (s:sx))]++unifyPredicate xs (Predicate n (s:sx)) 

applySolSubBody (Yes(x:xs))[] =[]
applySolSubBody(Yes(x:xs))((Predicate n (s:sx)):ys) =(unifyPredicate(x:xs) (Predicate n (s:sx)))++applySolSubBody(Yes(x:xs)) ys

-----------------------------------------------------------PART D--------------------------------------------------------------------------------------------------
callUnifyPredicate x [] = []
callUnifyPredicate (x:xs)((Predicate n (s:sx)):ys) =[unifyPredicate(x:xs) (Predicate n (s:sx))]++callUnifyPredicate (x:xs) ys

isfact[]=True 
isfact((Constant x):xs)=True && isfact xs
isfact(Variable a:xs)=False

relevantRule (Predicate a (z:zs)) ([[(Predicate b x)],((Predicate c y):xs)])= if a==b then (Predicate b x):((Predicate c y):xs)else []
relevantRule (Predicate a (z:zs)) [[Predicate b x]]=[]
searchRelevantRule a [] =[]
searchRelevantRule(Predicate a (z:zs)) ([[Predicate b x]]:ys)=(relevantRule (Predicate a (z:zs))[[Predicate b x]])++searchRelevantRule (Predicate a (z:zs)) ys  
searchRelevantRule(Predicate a (z:zs)) ( [[Predicate b x],xs]:ys) =(relevantRule (Predicate a (z:zs))[[Predicate b x],xs])++searchRelevantRule (Predicate a (z:zs)) ys 

filterfact(Predicate a y)= if isfact y then[Predicate a y]else []

flatten [] =[] 
flatten(x:xs)= flat x++flatten xs
flat[] =[]
flat(s:xs)=s++flat xs
flat[s] =s

searchFilterFact [] =[]
searchFilterFact (y:ys) =filterfact y++searchFilterFact ys
checkConstant[]=True
checkConstant((Constant x):xs)=True&&checkConstant xs
checkConstant((Variable x):xs)=False
-----------------------TRY--->FILTERS THE DATABASE SO IT ONLY CONTAINS VALID ANSWERS--------------------------------- 
try(Predicate a (z:zs)) ([[Predicate b x]]:ys)= if mod (length (removeEmptyList(get (searchRelevantRule(Predicate a (z:zs)) ([[Predicate b x]]:ys)) ([[Predicate b x]]:ys) ))) 2==0
                                          then getSimlarties(removeEmptyList(get (searchRelevantRule(Predicate a (z:zs)) ([[Predicate b x]]:ys)) ([[Predicate b x]]:ys)))
                                          else getSimlarties(removeEmptyList(get (searchRelevantRule(Predicate a (z:zs)) ([[Predicate b x]]:ys)) ([[Predicate b x]]:ys))++[[]])

final (Predicate a (z:zs)) ([[Predicate b x]]:ys) =unify (Predicate a (z:zs)) (searchRelevantRule(Predicate a (z:zs)) ([[Predicate b x]]:ys))

get [] a =[]
get(z:zs) ([[Predicate b x]]:ys) =[(unify z (searchFilterFact(flatten ([[Predicate b x]]:ys))))]++get zs ([[Predicate b x]]:ys) 

removeEmptyList []=[]
removeEmptyList(x:xs)=if x==[] then removeEmptyList xs else x:removeEmptyList xs 

merge [] y =y
merge y [] =y
merge (x:xs) y =if contains x y then merge xs y else x:merge xs y

replin x  b []=[]
replin x  b (y:ys)=if x==y then b:replin x b ys  else y:replin x b ys
repl[] l =l
--replacment (x:y:xs) l = replacment xs (replin x y l)  

repl (x:y:xs) (Predicate a l) = repl xs (Predicate a (replin x y l)) 
replacment l []=[]
replacment l (x:xs)= (repl l x):replacment l xs

--replacment x [] =x
--replacment [] x=x
--replacment [] [] =[]
--replacment(xa:xb:xs)(ya:yb:ys)= if (xa==yb) then ya:xb:replacment xs ys else replacment xs ys 
removeFirst (x:xs)=xs
boolSearch [] l =True
boolSearch(x:xs) l=(contains x l)&& boolSearch xs l
about (Predicate a (z:zs)) ([[Predicate b x]]:ys)= replacment (final (Predicate a (z:zs)) ([[Predicate b x]]:ys)) (searchRelevantRule(Predicate a (z:zs)) ([[Predicate b x]]:ys))
callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys)=answer (removeFirst(about (Predicate a (z:zs)) ([[Predicate b x]]:ys))) (searchFilterFact(flatten ([[Predicate b x]]:ys)))
answer [] l=[]
answer (x:xs) l=[unifyWithHead x l]++answer xs l
removeYes []=[]
removeYes((No):xs)=[No]
removeYes((Yes x):xs)=if (x ==[])  then removeYes xs else (Yes x):removeYes xs
callanswer (Predicate a (z:zs)) ([[Predicate b x]]:ys) =if isEmptyTrue(callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys)) then [Yes[]]
    else 
       if not (isTrue (callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys)))
        then [No]
        else if length(removeYes (callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys)))==0
               then removeYes (callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys))
               else removeYes (callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys))
                --digInTo (callAbout (Predicate a (z:zs)) ([[Predicate b x]]:ys))


incommen (Yes x) (Yes y)=contain x y
contain [] []=[]
contain[] x=[]
contain x []=[]
contain(xv:xc:xs)(yv:yc:ys)=if xv==yv then contain xs ys else yv:yc:contain xs ys
isEmptyTrue []=True
isEmptyTrue ((Yes []):xs)=True&&isEmptyTrue xs
isEmptyTrue ((Yes(x:xs)):ys)=False
isEmptyTrue ((No):xs)=False

isTrue[]=True
isTrue ((Yes[]):xs)=True&&isTrue xs
isTrue ((Yes (x:xs)):ys)=True&&isTrue ys
isTrue ((No):xs) =False


--getAns [] l=False
--getAns (x:xs) l =[]
  --[(unifyWithHead x  l)]++getAns xs l
{--
allSolutions(Predicate a (z:zs)) ([[Predicate b x]]:ys)= 
   if checkConstant(z:zs) 
                       then if length (search (z:zs) (try(Predicate a (z:zs)) ([[Predicate b x]]:ys)))==0
                                                 then Yes[]
                                                 else No
                                                 else 
                                                 if length(try(Predicate a (z:zs)) ([[Predicate b x]]:ys))==0
                                                               then No
                                                               else Yes(replacment (final (Predicate a (z:zs)) ([[Predicate b x]]:ys))(try(Predicate a (z:zs)) ([[Predicate b x]]:ys)))             
                                                                                                             --}
-----------------------------------------------------------THE END--------------------------------------------------------------------




--applySolSubBody(Yes [Variable "X",Constant "slim",Variable "X",Constant "salah"])[(Predicate "male" [Constant "slim"]),(Predicate "male" [Constant "salah"]),(Predicate "Parent" [Constant "salah",Constant "youmna"]),(Predicate "Parent" [Constant "slim",Constant "sara"]),(Predicate "father" [Variable "X",Variable "Y"]),(Predicate "male" [Variable "X"]),(Predicate "Parent" [Variable "X",Variable "Y"])]
