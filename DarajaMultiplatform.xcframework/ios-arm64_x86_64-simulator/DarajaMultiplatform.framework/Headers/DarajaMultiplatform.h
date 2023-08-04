#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class DarajaMultiplatformDarajaEnvironment, DarajaMultiplatformDarajaPaymentResponse, DarajaMultiplatformDarajaResult<__covariant T>, DarajaMultiplatformDarajaTransactionType, DarajaMultiplatformDarajaTransactionResponse, DarajaMultiplatformDarajaToken, DarajaMultiplatformDaraja, DarajaMultiplatformDarajaBuilder, DarajaMultiplatformKotlinEnumCompanion, DarajaMultiplatformKotlinEnum<E>, DarajaMultiplatformKotlinArray<T>, DarajaMultiplatformDarajaException, DarajaMultiplatformDarajaResultFailure, DarajaMultiplatformKotlinNothing, DarajaMultiplatformDarajaResultSuccess<__covariant T>, DarajaMultiplatformKotlinThrowable, DarajaMultiplatformKotlinException, DarajaMultiplatformDarajaExceptionCompanion, DarajaMultiplatformDarajaPaymentRequestCompanion, DarajaMultiplatformDarajaPaymentRequest, DarajaMultiplatformDarajaPaymentResponseCompanion, DarajaMultiplatformDarajaTokenCompanion, DarajaMultiplatformDarajaTransactionResponseCompanion, DarajaMultiplatformQueryDarajaTransactionRequestCompanion, DarajaMultiplatformQueryDarajaTransactionRequest, DarajaMultiplatformKotlinx_serialization_coreSerializersModule, DarajaMultiplatformKotlinx_serialization_coreSerialKind;

@protocol DarajaMultiplatformKotlinComparable, DarajaMultiplatformKotlinx_serialization_coreKSerializer, DarajaMultiplatformKotlinIterator, DarajaMultiplatformKotlinx_serialization_coreEncoder, DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor, DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy, DarajaMultiplatformKotlinx_serialization_coreDecoder, DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy, DarajaMultiplatformKotlinx_serialization_coreCompositeEncoder, DarajaMultiplatformKotlinAnnotation, DarajaMultiplatformKotlinx_serialization_coreCompositeDecoder, DarajaMultiplatformKotlinx_serialization_coreSerializersModuleCollector, DarajaMultiplatformKotlinKClass, DarajaMultiplatformKotlinKDeclarationContainer, DarajaMultiplatformKotlinKAnnotatedElement, DarajaMultiplatformKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface DarajaMultiplatformBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface DarajaMultiplatformBase (DarajaMultiplatformBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface DarajaMultiplatformMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface DarajaMultiplatformMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorDarajaMultiplatformKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface DarajaMultiplatformNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface DarajaMultiplatformByte : DarajaMultiplatformNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface DarajaMultiplatformUByte : DarajaMultiplatformNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface DarajaMultiplatformShort : DarajaMultiplatformNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface DarajaMultiplatformUShort : DarajaMultiplatformNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface DarajaMultiplatformInt : DarajaMultiplatformNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface DarajaMultiplatformUInt : DarajaMultiplatformNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface DarajaMultiplatformLong : DarajaMultiplatformNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface DarajaMultiplatformULong : DarajaMultiplatformNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface DarajaMultiplatformFloat : DarajaMultiplatformNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface DarajaMultiplatformDouble : DarajaMultiplatformNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface DarajaMultiplatformBoolean : DarajaMultiplatformNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Daraja")))
@interface DarajaMultiplatformDaraja : DarajaMultiplatformBase
- (instancetype)initWithConsumerKey:(NSString * _Nullable)consumerKey consumerSecret:(NSString * _Nullable)consumerSecret passKey:(NSString * _Nullable)passKey environment:(DarajaMultiplatformDarajaEnvironment * _Nullable)environment __attribute__((swift_name("init(consumerKey:consumerSecret:passKey:environment:)"))) __attribute__((objc_designated_initializer));
- (DarajaMultiplatformDarajaResult<DarajaMultiplatformDarajaPaymentResponse *> *)initiateMpesaExpressPaymentBusinessShortCode:(NSString *)businessShortCode amount:(int32_t)amount phoneNumber:(NSString *)phoneNumber transactionType:(DarajaMultiplatformDarajaTransactionType *)transactionType transactionDesc:(NSString *)transactionDesc callbackUrl:(NSString *)callbackUrl accountReference:(NSString * _Nullable)accountReference __attribute__((swift_name("initiateMpesaExpressPayment(businessShortCode:amount:phoneNumber:transactionType:transactionDesc:callbackUrl:accountReference:)")));
- (DarajaMultiplatformDarajaResult<DarajaMultiplatformDarajaTransactionResponse *> *)queryMpesaTransactionBusinessShortCode:(NSString *)businessShortCode checkoutRequestID:(NSString *)checkoutRequestID __attribute__((swift_name("queryMpesaTransaction(businessShortCode:checkoutRequestID:)")));
- (DarajaMultiplatformDarajaResult<DarajaMultiplatformDarajaToken *> *)requestAccessToken __attribute__((swift_name("requestAccessToken()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Daraja.Builder")))
@interface DarajaMultiplatformDarajaBuilder : DarajaMultiplatformBase
- (instancetype)initWithConsumerKey:(NSString * _Nullable)consumerKey consumerSecret:(NSString * _Nullable)consumerSecret passKey:(NSString * _Nullable)passKey environment:(DarajaMultiplatformDarajaEnvironment * _Nullable)environment __attribute__((swift_name("init(consumerKey:consumerSecret:passKey:darajaEnvironment:)"))) __attribute__((objc_designated_initializer));
- (DarajaMultiplatformDaraja *)build __attribute__((swift_name("doInit()")));
- (DarajaMultiplatformDarajaBuilder *)doCopyConsumerKey:(NSString * _Nullable)consumerKey consumerSecret:(NSString * _Nullable)consumerSecret passKey:(NSString * _Nullable)passKey environment:(DarajaMultiplatformDarajaEnvironment * _Nullable)environment __attribute__((swift_name("doCopy(consumerKey:consumerSecret:passKey:darajaEnvironment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (DarajaMultiplatformDarajaBuilder *)isProduction __attribute__((swift_name("isProduction()")));
- (DarajaMultiplatformDarajaBuilder *)isSandbox __attribute__((swift_name("isSandbox()")));
- (DarajaMultiplatformDarajaBuilder *)setConsumerKeyConsumerKey:(NSString *)consumerKey __attribute__((swift_name("withConsumerKey(consumerKey:)")));
- (DarajaMultiplatformDarajaBuilder *)setConsumerSecretConsumerSecret:(NSString *)consumerSecret __attribute__((swift_name("withConsumerSecret(consumerSecret:)")));
- (DarajaMultiplatformDarajaBuilder *)setPassKeyPassKey:(NSString *)passKey __attribute__((swift_name("withPassKey(passKey:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol DarajaMultiplatformKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface DarajaMultiplatformKotlinEnum<E> : DarajaMultiplatformBase <DarajaMultiplatformKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) DarajaMultiplatformKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaEnvironment")))
@interface DarajaMultiplatformDarajaEnvironment : DarajaMultiplatformKotlinEnum<DarajaMultiplatformDarajaEnvironment *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) DarajaMultiplatformDarajaEnvironment *productionEnvironment __attribute__((swift_name("productionEnvironment")));
@property (class, readonly) DarajaMultiplatformDarajaEnvironment *sandboxEnvironment __attribute__((swift_name("sandboxEnvironment")));
+ (DarajaMultiplatformKotlinArray<DarajaMultiplatformDarajaEnvironment *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((swift_name("DarajaResult")))
@interface DarajaMultiplatformDarajaResult<__covariant T> : DarajaMultiplatformBase
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaResultError")))
@interface DarajaMultiplatformDarajaResultFailure : DarajaMultiplatformDarajaResult<DarajaMultiplatformKotlinNothing *>
- (instancetype)initWithException:(DarajaMultiplatformDarajaException *)exception __attribute__((swift_name("init(exception:)"))) __attribute__((objc_designated_initializer));
- (DarajaMultiplatformDarajaResultFailure *)doCopyException:(DarajaMultiplatformDarajaException *)exception __attribute__((swift_name("doCopy(exception:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) DarajaMultiplatformDarajaException *exception __attribute__((swift_name("exception")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaResultSuccess")))
@interface DarajaMultiplatformDarajaResultSuccess<__covariant T> : DarajaMultiplatformDarajaResult<T>
- (instancetype)initWithData:(T)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (DarajaMultiplatformDarajaResultSuccess<T> *)doCopyData:(T)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) T data __attribute__((swift_name("data")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaTransactionType")))
@interface DarajaMultiplatformDarajaTransactionType : DarajaMultiplatformKotlinEnum<DarajaMultiplatformDarajaTransactionType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) DarajaMultiplatformDarajaTransactionType *customerpaybillonline __attribute__((swift_name("customerpaybillonline")));
@property (class, readonly) DarajaMultiplatformDarajaTransactionType *customerbuygoodsonline __attribute__((swift_name("customerbuygoodsonline")));
+ (DarajaMultiplatformKotlinArray<DarajaMultiplatformDarajaTransactionType *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface DarajaMultiplatformKotlinThrowable : DarajaMultiplatformBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(DarajaMultiplatformKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(DarajaMultiplatformKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (DarajaMultiplatformKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) DarajaMultiplatformKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface DarajaMultiplatformKotlinException : DarajaMultiplatformKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(DarajaMultiplatformKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(DarajaMultiplatformKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaException")))
@interface DarajaMultiplatformDarajaException : DarajaMultiplatformKotlinException
- (instancetype)initWithRequestId:(NSString * _Nullable)requestId errorCode:(NSString * _Nullable)errorCode errorMessage:(NSString * _Nullable)errorMessage __attribute__((swift_name("init(requestId:errorCode:errorMessage:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(DarajaMultiplatformKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(DarajaMultiplatformKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) DarajaMultiplatformDarajaExceptionCompanion *companion __attribute__((swift_name("companion")));
- (DarajaMultiplatformDarajaException *)doCopyRequestId:(NSString * _Nullable)requestId errorCode:(NSString * _Nullable)errorCode errorMessage:(NSString * _Nullable)errorMessage __attribute__((swift_name("doCopy(requestId:errorCode:errorMessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSString * _Nullable errorCode __attribute__((swift_name("errorCode")));
@property NSString * _Nullable errorMessage __attribute__((swift_name("errorMessage")));
@property NSString * _Nullable requestId __attribute__((swift_name("requestId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaException.Companion")))
@interface DarajaMultiplatformDarajaExceptionCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformDarajaExceptionCompanion *shared __attribute__((swift_name("shared")));
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaPaymentRequest")))
@interface DarajaMultiplatformDarajaPaymentRequest : DarajaMultiplatformBase
- (instancetype)initWithBusinessShortCode:(NSString *)businessShortCode password:(NSString *)password phoneNumber:(NSString *)phoneNumber timestamp:(NSString *)timestamp transactionType:(NSString *)transactionType amount:(NSString *)amount partyA:(NSString *)partyA partyB:(NSString *)partyB callBackUrl:(NSString *)callBackUrl accountReference:(NSString *)accountReference transactionDesc:(NSString *)transactionDesc __attribute__((swift_name("init(businessShortCode:password:phoneNumber:timestamp:transactionType:amount:partyA:partyB:callBackUrl:accountReference:transactionDesc:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) DarajaMultiplatformDarajaPaymentRequestCompanion *companion __attribute__((swift_name("companion")));
- (DarajaMultiplatformDarajaPaymentRequest *)doCopyBusinessShortCode:(NSString *)businessShortCode password:(NSString *)password phoneNumber:(NSString *)phoneNumber timestamp:(NSString *)timestamp transactionType:(NSString *)transactionType amount:(NSString *)amount partyA:(NSString *)partyA partyB:(NSString *)partyB callBackUrl:(NSString *)callBackUrl accountReference:(NSString *)accountReference transactionDesc:(NSString *)transactionDesc __attribute__((swift_name("doCopy(businessShortCode:password:phoneNumber:timestamp:transactionType:amount:partyA:partyB:callBackUrl:accountReference:transactionDesc:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *accountReference __attribute__((swift_name("accountReference")));
@property (readonly) NSString *amount __attribute__((swift_name("amount")));
@property (readonly) NSString *businessShortCode __attribute__((swift_name("businessShortCode")));
@property (readonly) NSString *callBackUrl __attribute__((swift_name("callBackUrl")));
@property (readonly) NSString *partyA __attribute__((swift_name("partyA")));
@property (readonly) NSString *partyB __attribute__((swift_name("partyB")));
@property (readonly) NSString *password __attribute__((swift_name("password")));
@property (readonly) NSString *phoneNumber __attribute__((swift_name("phoneNumber")));
@property (readonly) NSString *timestamp __attribute__((swift_name("timestamp")));
@property (readonly) NSString *transactionDesc __attribute__((swift_name("transactionDesc")));
@property (readonly) NSString *transactionType __attribute__((swift_name("transactionType")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaPaymentRequest.Companion")))
@interface DarajaMultiplatformDarajaPaymentRequestCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformDarajaPaymentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaPaymentResponse")))
@interface DarajaMultiplatformDarajaPaymentResponse : DarajaMultiplatformBase
- (instancetype)initWithMerchantRequestID:(NSString *)merchantRequestID checkoutRequestID:(NSString *)checkoutRequestID responseCode:(NSString *)responseCode responseDescription:(NSString *)responseDescription customerMessage:(NSString *)customerMessage __attribute__((swift_name("init(merchantRequestID:checkoutRequestID:responseCode:responseDescription:customerMessage:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) DarajaMultiplatformDarajaPaymentResponseCompanion *companion __attribute__((swift_name("companion")));
- (DarajaMultiplatformDarajaPaymentResponse *)doCopyMerchantRequestID:(NSString *)merchantRequestID checkoutRequestID:(NSString *)checkoutRequestID responseCode:(NSString *)responseCode responseDescription:(NSString *)responseDescription customerMessage:(NSString *)customerMessage __attribute__((swift_name("doCopy(merchantRequestID:checkoutRequestID:responseCode:responseDescription:customerMessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSString *checkoutRequestID __attribute__((swift_name("checkoutRequestID")));
@property NSString *customerMessage __attribute__((swift_name("customerMessage")));
@property NSString *merchantRequestID __attribute__((swift_name("merchantRequestID")));
@property NSString *responseCode __attribute__((swift_name("responseCode")));
@property NSString *responseDescription __attribute__((swift_name("responseDescription")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaPaymentResponse.Companion")))
@interface DarajaMultiplatformDarajaPaymentResponseCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformDarajaPaymentResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaToken")))
@interface DarajaMultiplatformDarajaToken : DarajaMultiplatformBase
- (instancetype)initWithAccessToken:(NSString *)accessToken expiresIn:(NSString *)expiresIn __attribute__((swift_name("init(accessToken:expiresIn:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) DarajaMultiplatformDarajaTokenCompanion *companion __attribute__((swift_name("companion")));
- (DarajaMultiplatformDarajaToken *)doCopyAccessToken:(NSString *)accessToken expiresIn:(NSString *)expiresIn __attribute__((swift_name("doCopy(accessToken:expiresIn:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *accessToken __attribute__((swift_name("accessToken")));
@property (readonly) NSString *expiresIn __attribute__((swift_name("expiresIn")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaToken.Companion")))
@interface DarajaMultiplatformDarajaTokenCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformDarajaTokenCompanion *shared __attribute__((swift_name("shared")));
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaTransactionResponse")))
@interface DarajaMultiplatformDarajaTransactionResponse : DarajaMultiplatformBase
- (instancetype)initWithResponseCode:(NSString *)responseCode responseDescription:(NSString *)responseDescription merchantRequestID:(NSString *)merchantRequestID checkoutRequestID:(NSString *)checkoutRequestID resultCode:(NSString *)resultCode resultDescription:(NSString *)resultDescription __attribute__((swift_name("init(responseCode:responseDescription:merchantRequestID:checkoutRequestID:resultCode:resultDescription:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) DarajaMultiplatformDarajaTransactionResponseCompanion *companion __attribute__((swift_name("companion")));
- (DarajaMultiplatformDarajaTransactionResponse *)doCopyResponseCode:(NSString *)responseCode responseDescription:(NSString *)responseDescription merchantRequestID:(NSString *)merchantRequestID checkoutRequestID:(NSString *)checkoutRequestID resultCode:(NSString *)resultCode resultDescription:(NSString *)resultDescription __attribute__((swift_name("doCopy(responseCode:responseDescription:merchantRequestID:checkoutRequestID:resultCode:resultDescription:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *checkoutRequestID __attribute__((swift_name("checkoutRequestID")));
@property (readonly) NSString *merchantRequestID __attribute__((swift_name("merchantRequestID")));
@property (readonly) NSString *responseCode __attribute__((swift_name("responseCode")));
@property (readonly) NSString *responseDescription __attribute__((swift_name("responseDescription")));
@property (readonly) NSString *resultCode __attribute__((swift_name("resultCode")));
@property (readonly) NSString *resultDescription __attribute__((swift_name("resultDescription")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DarajaTransactionResponse.Companion")))
@interface DarajaMultiplatformDarajaTransactionResponseCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformDarajaTransactionResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("QueryDarajaTransactionRequest")))
@interface DarajaMultiplatformQueryDarajaTransactionRequest : DarajaMultiplatformBase
- (instancetype)initWithBusinessShortCode:(NSString *)businessShortCode password:(NSString *)password timestamp:(NSString *)timestamp checkoutRequestID:(NSString *)checkoutRequestID __attribute__((swift_name("init(businessShortCode:password:timestamp:checkoutRequestID:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) DarajaMultiplatformQueryDarajaTransactionRequestCompanion *companion __attribute__((swift_name("companion")));
- (DarajaMultiplatformQueryDarajaTransactionRequest *)doCopyBusinessShortCode:(NSString *)businessShortCode password:(NSString *)password timestamp:(NSString *)timestamp checkoutRequestID:(NSString *)checkoutRequestID __attribute__((swift_name("doCopy(businessShortCode:password:timestamp:checkoutRequestID:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *businessShortCode __attribute__((swift_name("businessShortCode")));
@property (readonly) NSString *checkoutRequestID __attribute__((swift_name("checkoutRequestID")));
@property (readonly) NSString *password __attribute__((swift_name("password")));
@property (readonly) NSString *timestamp __attribute__((swift_name("timestamp")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("QueryDarajaTransactionRequest.Companion")))
@interface DarajaMultiplatformQueryDarajaTransactionRequestCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformQueryDarajaTransactionRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

@interface DarajaMultiplatformDarajaResult (Extensions)
- (DarajaMultiplatformDarajaResult<id> *)onFailureAction:(void (^)(DarajaMultiplatformDarajaException *))action __attribute__((swift_name("onFailure(action:)")));
- (DarajaMultiplatformDarajaResult<id> *)onSuccessAction:(void (^)(id))action __attribute__((swift_name("onSuccess(action:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface DarajaMultiplatformKotlinEnumCompanion : DarajaMultiplatformBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) DarajaMultiplatformKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface DarajaMultiplatformKotlinArray<T> : DarajaMultiplatformBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(DarajaMultiplatformInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<DarajaMultiplatformKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface DarajaMultiplatformKotlinNothing : DarajaMultiplatformBase
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<DarajaMultiplatformKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<DarajaMultiplatformKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol DarajaMultiplatformKotlinx_serialization_coreKSerializer <DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy, DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((swift_name("KotlinIterator")))
@protocol DarajaMultiplatformKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol DarajaMultiplatformKotlinx_serialization_coreEncoder
@required
- (id<DarajaMultiplatformKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<DarajaMultiplatformKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<DarajaMultiplatformKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) DarajaMultiplatformKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor
@required

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NSArray<id<DarajaMultiplatformKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSArray<id<DarajaMultiplatformKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) DarajaMultiplatformKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol DarajaMultiplatformKotlinx_serialization_coreDecoder
@required
- (id<DarajaMultiplatformKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<DarajaMultiplatformKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (DarajaMultiplatformKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) DarajaMultiplatformKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol DarajaMultiplatformKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<DarajaMultiplatformKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) DarajaMultiplatformKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface DarajaMultiplatformKotlinx_serialization_coreSerializersModule : DarajaMultiplatformBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<DarajaMultiplatformKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<DarajaMultiplatformKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<DarajaMultiplatformKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<DarajaMultiplatformKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<DarajaMultiplatformKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol DarajaMultiplatformKotlinAnnotation
@required
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface DarajaMultiplatformKotlinx_serialization_coreSerialKind : DarajaMultiplatformBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol DarajaMultiplatformKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<DarajaMultiplatformKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<DarajaMultiplatformKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) DarajaMultiplatformKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol DarajaMultiplatformKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<DarajaMultiplatformKotlinKClass>)kClass provider:(id<DarajaMultiplatformKotlinx_serialization_coreKSerializer> (^)(NSArray<id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<DarajaMultiplatformKotlinKClass>)kClass serializer:(id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<DarajaMultiplatformKotlinKClass>)baseClass actualClass:(id<DarajaMultiplatformKotlinKClass>)actualClass actualSerializer:(id<DarajaMultiplatformKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<DarajaMultiplatformKotlinKClass>)baseClass defaultDeserializerProvider:(id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<DarajaMultiplatformKotlinKClass>)baseClass defaultDeserializerProvider:(id<DarajaMultiplatformKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<DarajaMultiplatformKotlinKClass>)baseClass defaultSerializerProvider:(id<DarajaMultiplatformKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol DarajaMultiplatformKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol DarajaMultiplatformKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol DarajaMultiplatformKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol DarajaMultiplatformKotlinKClass <DarajaMultiplatformKotlinKDeclarationContainer, DarajaMultiplatformKotlinKAnnotatedElement, DarajaMultiplatformKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
