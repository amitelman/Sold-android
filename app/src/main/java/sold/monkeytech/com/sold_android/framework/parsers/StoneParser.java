//package sold.monkeytech.com.sold_android.framework.parsers;
//
//import com.mid.mid.framework.models.Stone;
//import com.mid.mid.framework.parsers.abs.GeneralParser;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by MonkeyFather on 28/08/2017.
// */
//
//public class StoneParser extends GeneralParser<Stone> {
//    private boolean cachedObject = true;
//
//    public StoneParser(boolean cachedObject) {
//        this.cachedObject = cachedObject;
//    }
//
//    @Override
//    public Stone parseToSingle(JSONObject jo)  {
//        Stone stone = null;
//        try {
//            if(cachedObject){
//                if(jo.has("Id") && !jo.get("Id").equals(0))
//                    stone = getObjectFromCache(jo, "Id");
//                else
//                    stone = getObjectFromCache(jo, "CertificateId");
//                if(stone == null){
//                    stone = new Stone();
//                }
//            }else{
//                stone = new Stone();
//            }
//            stone.setStatus(safeParseString(jo, "Status"));
//            stone.setTransitDate(safeParseString(jo, "TransitDate"));
//            stone.setOnHold(safeParseBoolean(jo, "IsOnHold"));
//            stone.setOnHoldForCustomer(safeParseString(jo, "OnHoldForCustomer"));
//            stone.setOnHoldRemainingHours(safeParseInt(jo, "OnHoldRemainingHours"));
//            stone.setOnHoldSalesManId(safeParseInt(jo, "OnHoldSalesManId"));
//            stone.setBusinessRegionId(safeParseInt(jo, "BusinessRegionId"));
//            stone.setListPrice(safeParseInt(jo, "ListPrice"));
//            stone.setDiscount(safeParseDouble(jo, "Discount"));
//            stone.setPrice(safeParseDouble(jo, "Price"));
//            stone.setTotal(safeParseDouble(jo, "Total"));
//            stone.setMemoDestination(safeParseString(jo, "MemoDestination"));
//            stone.setSpecial(safeParseString(jo, "Special"));
//            stone.setSpecial2(safeParseString(jo, "Special2"));
//            stone.setOffTheNetEditable(safeParseBoolean(jo, "IsOffTheNetEditable"));
//            stone.setOfTheNetUnlimitedPeriodEditAvailable(safeParseBoolean(jo, "IsOffTheNetUnlimitedPeriodEditAvailable"));
//            stone.setHaveHistory(safeParseBoolean(jo, "HaveHistory"));
//            stone.setReservedForCustomer(safeParseBoolean(jo, "IsReservedForForCustomer"));
//            stone.setReservedFor(safeParseString(jo, "ReservedFor"));
//            stone.setTray(safeParseString(jo, "Tray"));
//            stone.setCostDiscountCode(safeParseString(jo, "CostDiscountCode"));
//            stone.setCostPerCaratCode(safeParseString(jo, "CostPerCaratCode"));
//            stone.setTotalCostCode(safeParseString(jo, "TotalCostCode"));
//            stone.setStoneHistorySequence(safeParseString(jo, "StoneHistorySequence"));
////            stone.setInternalOrder(safeParseString(jo, "StoneInternalOrder"));
//
//            if(jo.has("StoneInternalOrder") && !jo.isNull("StoneInternalOrder")){
//                try {
//                    stone.setStoneInternalOrderItem(new StoneInternalOrderParser().parseToSingle(jo.getJSONObject("StoneInternalOrder")));
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if(jo.has("OffTheNetInventoryItem") && !jo.isNull("OffTheNetInventoryItem")){
//                try {
//                    stone.setOffTheNetInventoryItem(new OffTheNetInventoryItemParser().parseToSingle(jo.getJSONObject("OffTheNetInventoryItem")));
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//            }
//            stone.setInventoryResultSetId(safeParseInt(jo, "InventoryResultSetId"));
//            stone.setChecked(safeParseBoolean(jo, "IsChecked"));
//            stone.setCertificateId(safeParseString(jo, "CertificateId"));
//            stone.setLotId(safeParseString(jo, "LotId"));
//            stone.setOriginalLotId(safeParseString(jo, "OriginalLotId"));
//            stone.setWeight(safeParseDouble(jo, "Weight"));
//            stone.setShape(safeParseString(jo, "Shape"));
//            stone.setColor(safeParseString(jo, "Color"));
//            stone.setClarity(safeParseString(jo, "Clarity"));
//            stone.setLab(safeParseString(jo, "Lab"));
//            stone.setCut(safeParseString(jo, "Cut"));
//            stone.setPolish(safeParseString(jo, "Polish"));
//            stone.setSymmetry(safeParseString(jo, "Symmetry"));
//            stone.setFluoresenceIntensity(safeParseString(jo, "FluoresenceIntensity"));
//            stone.setFluoresenceColor(safeParseString(jo, "FluoresenceColor"));
//            stone.setM1(safeParseDouble(jo, "M1"));
//            stone.setM2(safeParseDouble(jo, "M2"));
//            stone.setM3(safeParseDouble(jo, "M3"));
//            stone.setRatio(safeParseDouble(jo, "Ratio"));
//            stone.setDepth(safeParseDouble(jo, "Depth"));
//            stone.setTablePercent(safeParseDouble(jo, "TablePercent"));
//            stone.setTreatment(safeParseString(jo, "Treatment"));
//            stone.setCrownHeight(safeParseInt(jo, "CrownHeight"));
//            stone.setCrownAngle(safeParseInt(jo, "CrownAngle"));
//            stone.setPavillionDepth(safeParseDouble(jo, "PavillionDepth"));
//            stone.setPavillionAngle(safeParseDouble(jo, "PavillionAngle"));
//            stone.setGirdleThick(safeParseString(jo, "GirdleThick"));
//            stone.setGirdleThin(safeParseString(jo, "GirdleThin"));
//            stone.setGirdlePercent(safeParseDouble(jo, "GirdlePercent"));
//            stone.setGirdleCondition(safeParseString(jo, "GirdleCondition"));
//            stone.setCulet(safeParseString(jo, "Culet"));
//            stone.setCuletCondition(safeParseString(jo, "CuletCondition"));
//            stone.setLaserInscription(safeParseString(jo, "LaserInscription"));
//            stone.setFancyColorIntensity(safeParseString(jo, "FancyColorIntensity"));
//            stone.setFancyColorIntensityName(safeParseString(jo, "FancyColorIntensityName"));
//            stone.setFancyColorOvertone(safeParseString(jo, "FancyColorOvertone"));
//            stone.setFancyColorOvertoneName(safeParseString(jo, "FancyColorOvertoneName"));
//            stone.setFancyColorColorName(safeParseString(jo, "FancyColorColorName"));
//            stone.setFancyColorFullName(safeParseString(jo, "FancyColorFullName"));
//            stone.setDiscount2(safeParseDouble(jo, "Discount2"));
//            stone.setPrice2(safeParseDouble(jo, "Price2"));
//            stone.setTotal2(safeParseDouble(jo, "Total2"));
//            stone.setBuissnessRegionName(safeParseString(jo, "BusinessRegionName"));
//            stone.setCity(safeParseString(jo, "City"));
//            stone.setState(safeParseString(jo, "State"));
//            stone.setCountry(safeParseString(jo, "Country"));
//            stone.setComment(safeParseString(jo, "Comment"));
//            stone.setImageLink(safeParseString(jo, "ImageLink"));
//            stone.setCertificateLink(safeParseString(jo, "CertificateLink"));
//            stone.setVideoLink(safeParseString(jo, "VideoLink"));
//            stone.setParcelUnit(safeParseString(jo, "ParcelUnit"));
//            stone.setShapeFullName(safeParseString(jo, "ShapeFullName"));
//            stone.setColorFullName(safeParseString(jo, "ColorFullName"));
//            stone.setStoneAvailabilityId(safeParseString(jo, "StoneAvailabilityId"));
//            stone.setStoneAvailabilityNick(safeParseString(jo, "StoneAvailabilityAvailabilityNick"));
//            stone.setAvailabilityLabel(safeParseString(jo, "StoneAvailabilityLabel"));
//            stone.setVerificationUrl(safeParseString(jo, "VerificationUrl"));
//            stone.setMatchPair(safeParseBoolean(jo, "IsMatchPair"));
//            stone.setPairLotId(safeParseString(jo, "PairLotId"));
//            stone.setIsMatchSeparable(safeParseBoolean(jo, "IsMatchSeparable"));
//            stone.setImageFileExtension(safeParseString(jo, "ImageFileExtension"));
//            stone.setImageFileExist(safeParseBoolean(jo, "IsImageFileExists"));
//            stone.setCertificateFileExists(safeParseBoolean(jo, "IsCertificateFileExist"));
//            stone.setCertificateFileExtension(safeParseString(jo, "CertificateFileExtension"));
//            stone.setIsCertificateDiagramImageExists(safeParseBoolean(jo, "IsCertificateDiagramImageExists"));
//            stone.setIsCertificatePlotImageExists(safeParseBoolean(jo, "IsCertificatePlotImageExists"));
//            stone.setIsv30Exists(safeParseBoolean(jo, "Isv360Exists"));
//            stone.setNewArrival(safeParseBoolean(jo, "IsNewArrival"));
//            stone.setAllowOnRap(safeParseBoolean(jo, "AllowOnRap"));
//            stone.setClarityDescription(safeParseString(jo, "ClarityDescription"));
//            stone.setShade(safeParseString(jo, "Shade"));
//            stone.setMilky(safeParseString(jo, "Milky"));
//            stone.setBlackInclusion(safeParseString(jo, "BlackInclusion"));
//            stone.setCentralInclusion(safeParseString(jo, "CentralInclusion"));
//            stone.setPrimaryStone(safeParseBoolean(jo, "IsPrimaryStone"));
//            if(jo.has("DiamondMedia") && !jo.isNull("DiamondMedia")){
//                try {
//                    stone.setDiamondMedia(new DiamondMediaParser().parseToSingle(jo.getJSONObject("DiamondMedia")));
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//            }
//            stone.setJeweleryDescription(safeParseString(jo, "JewelryDescription"));
//            if(jo.has("Pair") && !jo.isNull("Pair"))
//                stone.setPairStone(new StoneParser(true).parseToSingle(jo.getJSONObject("Pair")));
//
//            if(jo.has("IsNotAvailableForOrder") && !jo.isNull("IsNotAvailableForOrder")){
//                stone.setNotAvailableForOrder(safeParseBoolean(jo, "IsNotAvailableForOrder"));
//                stone.setNonAvailabilityReason(safeParseString(jo, "NonAvailabilityReason"));
//            }
//
//            if(jo.has("ForMember") && !jo.isNull("ForMember")){
//                stone.setForMember(safeParseString(jo, "ForMember"));
//            }
//
//            if(jo.has("ForMemberId") && !jo.isNull("ForMemberId")){
//                stone.setForMemberId(safeParseInt(jo, "ForMemberId"));
//            }
//
//            if(jo.has("OriginalOffice") && !jo.isNull("OriginalOffice")){
//                stone.setOriginalOffice(safeParseString(jo, "OriginalOffice"));
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        return stone;
//    }
//
//    @Override
//    protected Class getType() {
//        return Stone.class;
//    }
//}
//
