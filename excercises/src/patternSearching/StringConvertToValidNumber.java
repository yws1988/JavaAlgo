package patternSearching;

/// <summary>
/// Is the string be able to converted into a scentifique number
/// for example "2e1.2" is a scentifique number
/// </summary>
/// <param name="s"></param>
/// <returns></returns>

public class StringConvertToValidNumber {
    public static boolean isNumber(String str)
    {
        str = str.trim();
        var eI = str.split("e");
        if (str.indexOf(' ') != -1) return false;
        if (eI.length > 2) return false;

        try{
            if (eI.length == 1)
            {
                Double.parseDouble(str);
            }
            if (eI.length == 2)
            {
                Double.parseDouble(eI[0]);
                Double.parseDouble(eI[1]);
            }
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }
}
