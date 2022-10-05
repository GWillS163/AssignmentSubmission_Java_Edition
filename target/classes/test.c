string str = DateTime.Now.ToString("yyyy-MM-dd");
Console.WriteLine("Start to " + str);
var sets = new HashSet<string>();
for (int i = 0; i < thTableDD.Rows.Count; i++)
{
    if (str == thTableDD.Rows[i]["预算校验日期"].ToString())
    {
        string lis = thTableDD.Rows[i]["预算校验日期"].ToString();
        string sqdh = thTableDD.Rows[i]["申请单号"].ToString();
        sets.Add(sqdh);
    }
};
Console.WriteLine("第一个表当天日期的数量是" + sets.Count);
