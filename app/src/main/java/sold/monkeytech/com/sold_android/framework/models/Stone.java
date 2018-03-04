//package sold.monkeytech.com.sold_android.framework.models;
//
//import android.util.Log;
//
//import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
//import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;
//
//
///**
// * Created by MonkeyFather on 28/09/2017.
// */
//
//public class Stone extends BaseModel<Stone> {
//
//    private String status;
//
//
//    @Override
//    public BaseCache getInstanceOfCache() {
//        return StoneCache.getInstance();
//    }
//
//    @Override
//    protected Class getType() {
//        return Stone.class;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getTransitDate() {
//        return transitDate;
//    }
//
//    public void setTransitDate(String transitDate) {
//        this.transitDate = transitDate;
//    }
//
//    public Boolean getOnHold() {
//        return isOnHold;
//    }
//
//    public void setOnHold(Boolean onHold) {
//        isOnHold = onHold;
//    }
//
//    public String getOnHoldForCustomer() {
//        return onHoldForCustomer;
//    }
//
//    public void setOnHoldForCustomer(String onHoldForCustomer) {
//        this.onHoldForCustomer = onHoldForCustomer;
//    }
//
//    public int getOnHoldRemainingHours() {
//        return onHoldRemainingHours;
//    }
//
//    public void setOnHoldRemainingHours(int onHoldRemainingHours) {
//        this.onHoldRemainingHours = onHoldRemainingHours;
//    }
//
//    public int getOnHoldSalesRegionId() {
//        return onHoldSalesRegionId;
//    }
//
//    public void setOnHoldSalesRegionId(int onHoldSalesRegionId) {
//        this.onHoldSalesRegionId = onHoldSalesRegionId;
//    }
//
//    public int getOnHoldSalesManId() {
//        return onHoldSalesManId;
//    }
//
//    public void setOnHoldSalesManId(int onHoldSalesManId) {
//        this.onHoldSalesManId = onHoldSalesManId;
//    }
//
//    public int getBusinessRegionId() {
//        return businessRegionId;
//    }
//
//    public void setBusinessRegionId(int businessRegionId) {
//        this.businessRegionId = businessRegionId;
//    }
//
//    public int getListPrice() {
//        return listPrice;
//    }
//
//    public void setListPrice(int listPrice) {
//        this.listPrice = listPrice;
//    }
//
//    public Double getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(Double discount) {
//        this.discount = discount;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }
//
//    public String getMemoDestination() {
//        return memoDestination;
//    }
//
//    public void setMemoDestination(String memoDestination) {
//        this.memoDestination = memoDestination;
//    }
//
//    public String getSpecial() {
//        return special;
//    }
//
//    public void setSpecial(String special) {
//        this.special = special;
//    }
//
//    public String getSpecial2() {
//        return special2;
//    }
//
//    public void setSpecial2(String special2) {
//        this.special2 = special2;
//    }
//
//    public Boolean getOffTheNetEditable() {
//        return isOffTheNetEditable;
//    }
//
//    public void setOffTheNetEditable(Boolean offTheNetEditable) {
//        isOffTheNetEditable = offTheNetEditable;
//    }
//
//    public Boolean getOfTheNetUnlimitedPeriodEditAvailable() {
//        return isOfTheNetUnlimitedPeriodEditAvailable;
//    }
//
//    public void setOfTheNetUnlimitedPeriodEditAvailable(Boolean ofTheNetUnlimitedPeriodEditAvailable) {
//        isOfTheNetUnlimitedPeriodEditAvailable = ofTheNetUnlimitedPeriodEditAvailable;
//    }
//
//    public Boolean getHaveHistory() {
//        return haveHistory;
//    }
//
//    public void setHaveHistory(Boolean haveHistory) {
//        this.haveHistory = haveHistory;
//    }
//
//    public Boolean getReservedForCustomer() {
//        return isReservedForCustomer;
//    }
//
//    public void setReservedForCustomer(Boolean reservedForCustomer) {
//        isReservedForCustomer = reservedForCustomer;
//    }
//
//    public String getReservedFor() {
//        return reservedFor;
//    }
//
//    public void setReservedFor(String reservedFor) {
//        this.reservedFor = reservedFor;
//    }
//
//    public String getTray() {
//        return tray;
//    }
//
//    public void setTray(String tray) {
//        this.tray = tray;
//    }
//
//    public String getCostDiscountCode() {
//        return costDiscountCode;
//    }
//
//    public void setCostDiscountCode(String costDiscountCode) {
//        this.costDiscountCode = costDiscountCode;
//    }
//
//    public String getCostPerCaratCode() {
//        return costPerCaratCode;
//    }
//
//    public void setCostPerCaratCode(String costPerCaratCode) {
//        this.costPerCaratCode = costPerCaratCode;
//    }
//
//    public String getTotalCostCode() {
//        return totalCostCode;
//    }
//
//    public void setTotalCostCode(String totalCostCode) {
//        this.totalCostCode = totalCostCode;
//    }
//
//    public String getStoneHistorySequence() {
//        return stoneHistorySequence;
//    }
//
//    public void setStoneHistorySequence(String stoneHistorySequence) {
//        this.stoneHistorySequence = stoneHistorySequence;
//    }
//
//    public StoneInternalOrder getStoneInternalOrderItem() {
//        return stoneInternalOrderItem;
//    }
//
//    public void setStoneInternalOrderItem(StoneInternalOrder stoneInternalOrderItem) {
//        this.stoneInternalOrderItem = stoneInternalOrderItem;
//    }
//
//    //    public String getInternalOrder() {
////        return internalOrder;
////    }
////
////    public void setInternalOrder(String internalOrder) {
////        this.internalOrder = internalOrder;
////    }
//
//    public int getInventoryResultSetId() {
//        return inventoryResultSetId;
//    }
//
//    public void setInventoryResultSetId(int inventoryResultSetId) {
//        this.inventoryResultSetId = inventoryResultSetId;
//    }
//
//    public Boolean getChecked() {
//        return isChecked;
//    }
//
//    public void setChecked(Boolean checked) {
//        isChecked = checked;
//    }
//
//    public String getCertificateId() {
//        return certificateId;
//    }
//
//    public void setCertificateId(String certificateId) {
//        this.certificateId = certificateId;
//    }
//
//    public String getLotId() {
//        return lotId;
//    }
//
//    public void setLotId(String lotId) {
//        this.lotId = lotId;
//    }
//
//    public String getOriginalLotId() {
//        return originalLotId;
//    }
//
//    public void setOriginalLotId(String originalLotId) {
//        this.originalLotId = originalLotId;
//    }
//
//    public Double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Double weight) {
//        this.weight = weight;
//    }
//
//    public String getShape() {
//        return shape;
//    }
//
//    public void setShape(String shape) {
//        this.shape = shape;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getClarity() {
//        return clarity;
//    }
//
//    public void setClarity(String clarity) {
//        this.clarity = clarity;
//    }
//
//    public String getLab() {
//        return lab;
//    }
//
//    public void setLab(String lab) {
//        this.lab = lab;
//    }
//
//    public String getCut() {
//        return cut;
//    }
//
//    public void setCut(String cut) {
//        this.cut = cut;
//    }
//
//    public String getPolish() {
//        return polish;
//    }
//
//    public void setPolish(String polish) {
//        this.polish = polish;
//    }
//
//    public String getSymmetry() {
//        return symmetry;
//    }
//
//    public void setSymmetry(String symmetry) {
//        this.symmetry = symmetry;
//    }
//
//    public String getFluoresenceIntensity() {
//        return fluoresenceIntensity;
//    }
//
//    public void setFluoresenceIntensity(String fluoresenceIntensity) {
//        this.fluoresenceIntensity = fluoresenceIntensity;
//    }
//
//    public String getFluoresenceColor() {
//        return fluoresenceColor;
//    }
//
//    public void setFluoresenceColor(String fluoresenceColor) {
//        this.fluoresenceColor = fluoresenceColor;
//    }
//
//    public Double getM1() {
//        return M1;
//    }
//
//    public void setM1(Double m1) {
//        M1 = m1;
//    }
//
//    public Double getM2() {
//        return M2;
//    }
//
//    public void setM2(Double m2) {
//        M2 = m2;
//    }
//
//    public Double getM3() {
//        return M3;
//    }
//
//    public void setM3(Double m3) {
//        M3 = m3;
//    }
//
//    public double getRatio() {
//        return ratio;
//    }
//
//    public void setRatio(double ratio) {
//        this.ratio = ratio;
//    }
//
//    public Double getDepth() {
//        return depth;
//    }
//
//    public void setDepth(Double depth) {
//        this.depth = depth;
//    }
//
//    public double getTablePercent() {
//        return tablePercent;
//    }
//
//    public void setTablePercent(double tablePercent) {
//        this.tablePercent = tablePercent;
//    }
//
//    public String getTreatment() {
//        return treatment;
//    }
//
//    public void setTreatment(String treatment) {
//        this.treatment = treatment;
//    }
//
//    public int getCrownHeight() {
//        return crownHeight;
//    }
//
//    public void setCrownHeight(int crownHeight) {
//        this.crownHeight = crownHeight;
//    }
//
//    public int getCrownAngle() {
//        return crownAngle;
//    }
//
//    public void setCrownAngle(int crownAngle) {
//        this.crownAngle = crownAngle;
//    }
//
//    public double getPavillionDepth() {
//        return pavillionDepth;
//    }
//
//    public void setPavillionDepth(double pavillionDepth) {
//        this.pavillionDepth = pavillionDepth;
//    }
//
//    public double getPavillionAngle() {
//        return pavillionAngle;
//    }
//
//    public void setPavillionAngle(double pavillionAngle) {
//        this.pavillionAngle = pavillionAngle;
//    }
//
//    public String getGirdleThick() {
//        return girdleThick;
//    }
//
//    public void setGirdleThick(String girdleThick) {
//        this.girdleThick = girdleThick;
//    }
//
//    public String getGirdleThin() {
//        return girdleThin;
//    }
//
//    public void setGirdleThin(String girdleThin) {
//        this.girdleThin = girdleThin;
//    }
//
//    public double getGirdlePercent() {
//        return girdlePercent;
//    }
//
//    public void setGirdlePercent(double girdlePercent) {
//        this.girdlePercent = girdlePercent;
//    }
//
//    public String getGirdleCondition() {
//        return girdleCondition;
//    }
//
//    public void setGirdleCondition(String girdleCondition) {
//        this.girdleCondition = girdleCondition;
//    }
//
//    public String getCulet() {
//        return culet;
//    }
//
//    public void setCulet(String culet) {
//        this.culet = culet;
//    }
//
//    public String getCuletCondition() {
//        return culetCondition;
//    }
//
//    public void setCuletCondition(String culetCondition) {
//        this.culetCondition = culetCondition;
//    }
//
//    public String getLaserInscription() {
//        return laserInscription;
//    }
//
//    public void setLaserInscription(String laserInscription) {
//        this.laserInscription = laserInscription;
//    }
//
//    public String getFancyColorIntensity() {
//        return fancyColorIntensity;
//    }
//
//    public void setFancyColorIntensity(String fancyColorIntensity) {
//        this.fancyColorIntensity = fancyColorIntensity;
//    }
//
//    public String getFancyColorOvertone() {
//        return fancyColorOvertone;
//    }
//
//    public void setFancyColorOvertone(String fancyColorOvertone) {
//        this.fancyColorOvertone = fancyColorOvertone;
//    }
//
//    public String getFancyColorIntensityName() {
//        return fancyColorIntensityName;
//    }
//
//    public void setFancyColorIntensityName(String fancyColorIntensityName) {
//        this.fancyColorIntensityName = fancyColorIntensityName;
//    }
//
//    public String getFancyColorOvertoneName() {
//        return fancyColorOvertoneName;
//    }
//
//    public void setFancyColorOvertoneName(String fancyColorOvertoneName) {
//        this.fancyColorOvertoneName = fancyColorOvertoneName;
//    }
//
//    public String getFancyColorColorName() {
//        return fancyColorColorName;
//    }
//
//    public void setFancyColorColorName(String fancyColorColorName) {
//        this.fancyColorColorName = fancyColorColorName;
//    }
//
//    public String getFancyColorFullName() {
//        return fancyColorFullName;
//    }
//
//    public void setFancyColorFullName(String fancyColorFullName) {
//        this.fancyColorFullName = fancyColorFullName;
//    }
//
//    public Double getDiscount2() {
//        return discount2;
//    }
//
//    public void setDiscount2(Double discount2) {
//        this.discount2 = discount2;
//    }
//
//    public Double getPrice2() {
//        return price2;
//    }
//
//    public void setPrice2(Double price2) {
//        this.price2 = price2;
//    }
//
//    public Double getTotal2() {
//        return total2;
//    }
//
//    public void setTotal2(Double total2) {
//        this.total2 = total2;
//    }
//
//    public String getBuissnessRegionName() {
//        if(TextUtils.isEmpty(buissnessRegionName))
//            return getCountry();
//        return buissnessRegionName;
//    }
//
//    public void setBuissnessRegionName(String buissnessRegionName) {
//        this.buissnessRegionName = buissnessRegionName;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public String getImageLink() {
//        return imageLink;
//    }
//
//    public void setImageLink(String imageLink) {
//        this.imageLink = imageLink;
//    }
//
//    public String getCertificateLink() {
//        return certificateLink;
//    }
//
//    public void setCertificateLink(String certificateLink) {
//        this.certificateLink = certificateLink;
//    }
//
//    public String getVideoLink() {
//        return videoLink;
//    }
//
//    public void setVideoLink(String videoLink) {
//        this.videoLink = videoLink;
//    }
//
//    public String getParcelUnit() {
//        return parcelUnit;
//    }
//
//    public void setParcelUnit(String parcelUnit) {
//        this.parcelUnit = parcelUnit;
//    }
//
//    public String getShapeFullName() {
//        if(TextUtils.isEmpty(shapeFullName))
//            return getShape();
//        return shapeFullName;
//    }
//
//    public void setShapeFullName(String shapeFullName) {
//        this.shapeFullName = shapeFullName;
//    }
//
//    public String getColorFullName() {
//        return colorFullName;
//    }
//
//    public void setColorFullName(String colorFullName) {
//        this.colorFullName = colorFullName;
//    }
//
//    public String getStoneAvailabilityId() {
//        return stoneAvailabilityId;
//    }
//
//    public void setStoneAvailabilityId(String stoneAvailabilityId) {
//        this.stoneAvailabilityId = stoneAvailabilityId;
//    }
//
//    public String getStoneAvailabilityNick() {
//        return stoneAvailabilityNick;
//    }
//
//    public void setStoneAvailabilityNick(String stoneAvailabilityNick) {
//        this.stoneAvailabilityNick = stoneAvailabilityNick;
//    }
//
//    public String getVerificationUrl() {
//        return verificationUrl;
//    }
//
//    public void setVerificationUrl(String verificationUrl) {
//        this.verificationUrl = verificationUrl;
//    }
//
//    public Boolean isMatchPair() {
//        return isMatchPair;
//    }
//
//    public void setMatchPair(Boolean matchPair) {
//        isMatchPair = matchPair;
//    }
//
//    public String getPairLotId() {
//        return pairLotId;
//    }
//
//    public void setPairLotId(String pairLotId) {
//        this.pairLotId = pairLotId;
//    }
//
//    public Boolean isMatchSeparable() {
//        return isMatchSeparable;
//    }
//
//    public void setIsMatchSeparable(Boolean isMatchSeparable) {
//        this.isMatchSeparable = isMatchSeparable;
//    }
//
//    public String getImageFileExtension() {
//        return imageFileExtension;
//    }
//
//    public void setImageFileExist(Boolean isImageFileExist) {
//        isImageFileExist = isImageFileExist;
//    }
//
//    public Boolean getCertificateFileExtension() {
//        return isImageFileExist;
//    }
//
//    public Boolean getCertificateFileExists() {
//        return isCertificateFileExists;
//    }
//
//    public void setCertificateFileExists(Boolean certificateFileExists) {
//        isCertificateFileExists = certificateFileExists;
//    }
//
//    public void setCertificateFileExtension(String certificateFileExtension) {
//        this.certificateFileExtension = certificateFileExtension;
//    }
//
//    public Boolean getIsCertificateDiagramImageExists() {
//        return isCertificateDiagramImageExists;
//    }
//
//    public void setIsCertificateDiagramImageExists(Boolean isCertificateDiagramImageExists) {
//        this.isCertificateDiagramImageExists = isCertificateDiagramImageExists;
//    }
//
//    public Boolean getIsCertificatePlotImageExists() {
//        return isCertificatePlotImageExists;
//    }
//
//    public void setIsCertificatePlotImageExists(Boolean isCertificatePlotImageExists) {
//        this.isCertificatePlotImageExists = isCertificatePlotImageExists;
//    }
//
//    public Boolean getIsv30Exists() {
//        return isv30Exists;
//    }
//
//    public void setIsv30Exists(Boolean isv30Exists) {
//        this.isv30Exists = isv30Exists;
//    }
//
//    public Boolean getNewArrival() {
//        return isNewArrival;
//    }
//
//    public void setNewArrival(Boolean newArrival) {
//        isNewArrival = newArrival;
//    }
//
//    public Boolean getAllowOnRap() {
//        return allowOnRap;
//    }
//
//    public void setAllowOnRap(Boolean allowOnRap) {
//        this.allowOnRap = allowOnRap;
//    }
//
//    public String getClarityDescription() {
//        return clarityDescription;
//    }
//
//    public void setClarityDescription(String clarityDescription) {
//        this.clarityDescription = clarityDescription;
//    }
//
//    public String getShade() {
//        return shade;
//    }
//
//    public void setShade(String shade) {
//        this.shade = shade;
//    }
//
//    public String getMilky() {
//        return milky;
//    }
//
//    public void setMilky(String milky) {
//        this.milky = milky;
//    }
//
//    public String getBlackInclusion() {
//        return blackInclusion;
//    }
//
//    public void setBlackInclusion(String blackInclusion) {
//        this.blackInclusion = blackInclusion;
//    }
//
//    public String getCentralInclusion() {
//        return centralInclusion;
//    }
//
//    public void setCentralInclusion(String centralInclusion) {
//        this.centralInclusion = centralInclusion;
//    }
//
//    public Boolean getPrimaryStone() {
//        return isPrimaryStone;
//    }
//
//    public void setPrimaryStone(Boolean primaryStone) {
//        isPrimaryStone = primaryStone;
//    }
//
//    public String getJeweleryDescription() {
//        return jeweleryDescription;
//    }
//
//    public void setJeweleryDescription(String jeweleryDescription) {
//        this.jeweleryDescription = jeweleryDescription;
//    }
//
//    public String getPair() {
//        return pair;
//    }
//
//    public void setPair(String pair) {
//        this.pair = pair;
//    }
//
//    public OffTheNetInventoryItem getOffTheNetInventoryItem() {
//        return offTheNetInventoryItem;
//    }
//
//    public void setOffTheNetInventoryItem(OffTheNetInventoryItem offTheNetInventoryItem) {
//        this.offTheNetInventoryItem = offTheNetInventoryItem;
//    }
//
//    public DiamondMedia getDiamondMedia() {
//        return diamondMedia;
//    }
//
//    public void setDiamondMedia(DiamondMedia diamondMedia) {
//        this.diamondMedia = diamondMedia;
//    }
//
//    public void setImageFileExtension(String imageFileExtension) {
//        this.imageFileExtension = imageFileExtension;
//    }
//
//    public Stone getPairStone() {
//        return pairStone;
//    }
//
//    public void setPairStone(Stone pairStone) {
//        this.pairStone = pairStone;
//    }
//
//    public Boolean getNotAvailableForOrder() {
//        return IsNotAvailableForOrder;
//    }
//
//    public void setNotAvailableForOrder(Boolean notAvailableForOrder) {
//        IsNotAvailableForOrder = notAvailableForOrder;
//    }
//
//    public String getNonAvailabilityReason() {
//        return NonAvailabilityReason;
//    }
//
//    public void setNonAvailabilityReason(String nonAvailabilityReason) {
//        NonAvailabilityReason = nonAvailabilityReason;
//    }
//
//    public long getForMemberId() {
//        return forMemberId;
//    }
//
//    public void setForMemberId(long forMemberId) {
//        this.forMemberId = forMemberId;
//    }
//
//    public String getForMember() {
//        return forMember;
//    }
//
//    public void setForMember(String forMember) {
//        this.forMember = forMember;
//    }
//
//    public String getOriginalOffice() {
//        return originalOffice;
//    }
//
//    public void setOriginalOffice(String originalOffice) {
//        this.originalOffice = originalOffice;
//    }
//
//    public String getAvailabilityLabel() {
//        return availabilityLabel;
//    }
//
//    public void setAvailabilityLabel(String availabilityLabel) {
//        this.availabilityLabel = availabilityLabel;
//    }
//
//    public String getMM() {
//        if(getM1() != null && getM2() != null && getM3() != null){
//                return getM1() + "x" + getM2() + "x" + getM3();
//            }
//        return "";
//    }
//
//    public double getTotalShort() {
//        if(getTotal() != null){
//            double val = (((int)(getTotal() * 100)))/100;
//            Log.d("wow","getTotalShort: " + val);
//            return val;
////            String parsedDouble = new DecimalFormat("##.##").format(getTotal());
////            return Double.valueOf(parsedDouble);
//        }
//        return 0;
//    }
//
//    public double getDiscountShort() {
//        if(getDiscount() != null){
//            int val = (int) (getDiscount() * 100);
//            double val2 = ((double)val)/100;
//            Log.d("wow","getDiscountShort: " + val2);
//            return val2;
//        }
//        return 0;
//    }
//
//    public double getPriceShort() {
//        if(getPrice() != null){
//            int val = (int) (getPrice() * 100);
//            double val2 = ((double)val)/100;
//            Log.d("wow","getPriceShort: " + val2);
//            return val2;
//        }
//        return 0;
//    }
//
//    public boolean isWithTreatment(){
//        if(!TextUtils.isEmpty(getTreatment()) && !getTreatment().equals("Not Treated"))
//            return true;
//        else return false;
//
//    }
//}
